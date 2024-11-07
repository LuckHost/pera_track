package com.peratrack.data.room.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ReceiptWithProducts(
    @Embedded val receipt: Receipt,
    @Relation(
        parentColumn = "uid",
        entityColumn = "productId",
    )
    val products: List<Product>
)
