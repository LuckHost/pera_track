package com.peratrack.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.peratrack.data.room.models.Receipt
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM receipt")
    fun getAll(): Single<List<Receipt>>

    @Query("SELECT * FROM receipt WHERE uid = :id")
    fun getReceiptById(id: Int): Maybe<Receipt>

    @Query("SELECT * FROM receipt WHERE date = :date AND " +
            "storeName = :storeName AND totalAmount = :totalAmount")
    fun findReceipt(date: Long, storeName: String, totalAmount: Float): Maybe<Receipt>

    @Insert
    fun insertAll(vararg receipts: Receipt): Completable

    @Delete
    fun delete(receipt: Receipt): Completable
}