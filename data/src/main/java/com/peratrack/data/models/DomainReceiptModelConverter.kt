package com.peratrack.data.models

import com.peratrack.data.room.Receipt
import com.peratrack.domain.models.Receipt as DomainReceipt

fun DomainReceipt.toReceiptDao() : Receipt {
    return Receipt(
        this.uid,
        this.date,
        this.storeName,
        this.totalAmount
    )
}

fun Receipt.toDomainReceipt() : DomainReceipt {
    return DomainReceipt(
        this.uid,
        this.date,
        this.storeName,
        this.totalAmount
    )
}