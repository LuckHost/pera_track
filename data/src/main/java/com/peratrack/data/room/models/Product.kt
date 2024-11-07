package com.peratrack.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "product",
    foreignKeys = [ForeignKey(
        entity = Receipt::class,
        parentColumns = ["uid"],
        childColumns = ["receiptId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Product(
    @PrimaryKey(autoGenerate = true) val productId: Int = 0,
    val receiptId: Int,  // Связь с Receipt
    val name: String,
    val count: Float,
    val countType: CountType,
    val amount: Float,
    val currency: String
) {
    enum class CountType {
        KILOGRAMS, UNITS, GRAMS
    }
}