package com.peratrack.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM receipt")
    fun getAll(): Single<List<Receipt>>

    @Insert
    fun insertAll(vararg receipts: Receipt): Completable

    @Delete
    fun delete(receipt: Receipt): Completable
}