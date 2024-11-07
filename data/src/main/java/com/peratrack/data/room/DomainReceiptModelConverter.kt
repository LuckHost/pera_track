package com.peratrack.data.room


import com.peratrack.data.room.models.Product
import com.peratrack.data.room.models.Receipt
import com.peratrack.domain.models.Product.CountType as DomainCountType
import com.peratrack.domain.models.Product as DomainProduct
import com.peratrack.domain.models.Receipt as DomainReceipt

fun DomainReceipt.toDataReceipt() : Receipt {
    return Receipt(
        this.uid,
        this.date,
        this.storeName,
        this.totalAmount,
    )
}

fun Receipt.toDomainReceipt(products: List<DomainProduct>) : DomainReceipt {
    return DomainReceipt(
        this.uid,
        this.date,
        this.storeName,
        this.totalAmount,
        products
    )
}

fun DomainProduct.toDataProduct(parentId: Int) : Product {
    val countType = when(this.countType) {
        DomainCountType.KILOGRAMS ->
            Product.CountType.KILOGRAMS
        DomainCountType.UNITS ->
            Product.CountType.UNITS
        DomainCountType.GRAMS ->
            Product.CountType.GRAMS
        else ->
            throw NullPointerException(
                "Count type was null! Receipt id: $parentId."
            )
    }

    return Product(
        0,
        parentId,
        this.name,
        this.count,
        countType,
        this.amount,
        this.currency
    )
}

fun Product.toDomainProduct() : DomainProduct {
    val countType = when(this.countType) {
        Product.CountType.KILOGRAMS ->
            DomainCountType.KILOGRAMS
        Product.CountType.UNITS ->
            DomainCountType.UNITS
        Product.CountType.GRAMS ->
            DomainCountType.GRAMS
        else ->
            throw NullPointerException(
                "Count type was null! Product id: ${this.productId}."
            )
    }

    return DomainProduct(
        this.name,
        this.count,
        countType,
        this.amount,
        this.currency
    )
}