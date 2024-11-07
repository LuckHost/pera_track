package com.peratrack.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.peratrack.data.room.models.Product

@Dao
interface ProductDao {

    @Insert
    fun insertProducts(products: List<Product>)

    @Query("SELECT * FROM product WHERE receiptId = :receiptId")
    fun getProductsForReceipt(receiptId: Int): List<Product>
}