package com.peratrack.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "receipt")
data class Receipt(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val date: Date?,
    val storeName: String?,
    val totalAmount: Float?,
)
