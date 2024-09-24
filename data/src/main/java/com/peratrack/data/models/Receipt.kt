package com.peratrack.data.models

import java.util.Date

internal data class Receipt(
    val date: Date,
    val storeName: String,
    val totalAmount: Float,
)
