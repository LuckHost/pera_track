package com.peratrack.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "receipt")
data class Receipt(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "date") val date: Date?,
    @ColumnInfo(name = "storeName") val storeName: String?,
    @ColumnInfo(name = "totalAmount") val totalAmount: Float?,
)
