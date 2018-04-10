package com.nurgle.android.uzhik.database

import android.arch.persistence.room.*

@Entity(tableName = "products")
data class Product(
        var name: String,
        var available: Boolean,
        var link: String="",
        var price : Int,
        var image : String=""
){
    @PrimaryKey(autoGenerate = true)
    var uid: Long? = null
    class List : ArrayList<Product>()
}

@Dao
interface ProductDAO {

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM products WHERE name =:name")
    fun getProduct(name: String):Product

     @Insert
      fun insert(product: Product)
}

