package com.peratrack.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.peratrack.data.room.AppDatabase
import com.peratrack.data.room.Receipt
import com.peratrack.domain.repositories.LocalReceiptsRepoInterface
import io.reactivex.rxjava3.schedulers.Schedulers
import com.peratrack.domain.models.Receipt as DomainReceipt


class LocalReceiptsRepoImpl(
    private val context: Context
) : LocalReceiptsRepoInterface {

    companion object {
        fun getDatabase(context: Context): AppDatabase {
            return  Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).build()
        }
    }


    override fun saveReceipt(receipt: DomainReceipt?) {
        val db = getDatabase(context)

        Log.d("DB", receipt.toString())

        val receiptEntity = Receipt(
            date = receipt?.date,
            storeName = receipt?.storeName,
            totalAmount = receipt?.totalAmount
        )

        Log.d("DB", receiptEntity.toString())

        db.receiptDao().insertAll(receiptEntity)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun deleteReceipt(receipt: DomainReceipt?) {
        TODO("Not yet implemented")
    }

    override fun getAllReceipts(): MutableList<DomainReceipt> {
        val db = getDatabase(context)
        val result = mutableListOf<DomainReceipt>()

        val d = db.receiptDao().getAll()
            .subscribeOn(Schedulers.io())
            .subscribe {
                it.forEach { item ->
                    result.add(
                        DomainReceipt(
                            item.date,
                            item.storeName,
                            item.totalAmount
                        )
                    )
                }
            }

        Log.d("DB", result.toString())

        return result
    }
}