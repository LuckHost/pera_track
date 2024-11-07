package com.peratrack.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.peratrack.data.room.models.Product
import com.peratrack.data.room.models.Receipt

@Database(entities = [Receipt::class, Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun receiptDao(): ReceiptDao
    abstract fun productDao(): ProductDao
}