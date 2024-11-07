package com.peratrack.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.peratrack.data.room.toDomainReceipt
import com.peratrack.data.room.AppDatabase
import com.peratrack.data.room.toDataProduct
import com.peratrack.data.room.toDataReceipt
import com.peratrack.data.room.toDomainProduct
import com.peratrack.domain.repositories.LocalReceiptsRepoInterface
import io.reactivex.rxjava3.schedulers.Schedulers
import com.peratrack.domain.models.Receipt as DomainReceipt

/**
 * Receipt repository implementation
 *
 * Works with the Room DB and RxJava
 * Blocks the IO thread to perform CRUD operations
 *
 * @param context App context to initialize the Room DataBase
 */
class LocalReceiptsRepoImpl(
    private val context: Context
) : LocalReceiptsRepoInterface {

    /**
     * DB initialize
     */
    companion object {
        fun getDatabase(context: Context): AppDatabase {
            return  Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).build()
        }
    }

    /**
     * Function that decides which objects need to be saved and saves them
     */
    override fun saveReceipt(receipt: DomainReceipt?) {
        val db = getDatabase(context)

        receipt?.let {
            if (receipt.uid == 0) {
                // Checking that an object is new
                // If so adding it
                /* TODO it a good idea to migrate on hashcode check */
                db.receiptDao().findReceipt(
                    receipt.date.time,
                    receipt.storeName,
                    receipt.totalAmount)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe({

                    }, { error ->
                        Log.e("DB", "Error checking receipt: $error")
                    }, {
                        // There is no such object, adding it
                        db.receiptDao().insertAll(receipt.toDataReceipt())
                            .subscribeOn(Schedulers.io())
                            .subscribe()

                        db.productDao().insertProducts(
                            receipt.products.map {
                                it.toDataProduct(receipt.uid)
                            }
                        )
                    })


            } else {
                // Checking that an object exist
                db.receiptDao().getReceiptById(receipt.uid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe({
                        // If so skip adding
                    }, { error ->
                        Log.e("DB", "Error checking receipt: $error")
                    }, {
                        // If not, then adding
                        db.receiptDao().insertAll(receipt.toDataReceipt())
                            .subscribeOn(Schedulers.io())
                            .subscribe()
                    })
            }
        }
    }

    override fun deleteReceipt(receipt: DomainReceipt?) {
        val db = getDatabase(context)

        if (receipt != null) {
            db.receiptDao().delete(receipt.toDataReceipt())
                .subscribeOn(Schedulers.io())
                .subscribe()
        }
    }

    /**
     * Getting receipts and converting them into the DomainReceipt objects
     */
    override fun getAllReceipts(): List<DomainReceipt> {
        val db = getDatabase(context)

        return db.receiptDao().getAll()
            .subscribeOn(Schedulers.io())
            .map { receiptEntities ->
                receiptEntities.map { entity ->
                    // Convert each Data Receipt to Domain Receipt
                    entity.toDomainReceipt(
                        // Get products for each receipt
                        db.productDao()
                            .getProductsForReceipt(entity.uid).map { product ->
                                // Convert each Data product to Domain Product
                                product.toDomainProduct()
                            }
                    )
                }
            }.blockingGet()
    }
}