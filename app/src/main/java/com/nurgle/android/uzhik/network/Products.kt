package com.nurgle.android.uzhik.network

import com.google.gson.JsonNull
import com.google.gson.JsonParser
import com.nurgle.android.uzhik.database.Product
import okhttp3.OkHttpClient
import okhttp3.Request

fun getSubscribtions(token : String) : List<Product>{

    val url = "http://88.206.52.198:7575/products"
    val products = mutableListOf<Product>()
    val client = OkHttpClient()
    val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $token")
            .build()
    val response = client.newCall(request).execute()
    val responseBody = response.body()!!.string()

    val parser = JsonParser()
    val jsonProducts = parser.parse(responseBody)
    val jsonArray = jsonProducts.getAsJsonArray()
    for (i in 0..(jsonArray.size() - 1)) {
        val item = jsonArray.get(i).asJsonObject
        val name = item["product"].asJsonObject["name"].asString
        val available = item["notificationSettings"].asJsonObject["availability"].asBoolean
        val link = item["product"].asJsonObject["link"].asString
        val image = item["product"].asJsonObject["image"].asString
        val history = item["product"].asJsonObject["history"].asJsonArray
        val price = history[history.size()-1].asJsonObject["price"].asInt
        val product = Product(name, available, link, price, image)
        products.add(product)
    }
    return products
}

fun findProducts(name: String): List<Product>{
    //http://localhost:7575/search?query=sony
    val url = "http://88.206.52.198:7575/search?query=$name"
    val products = mutableListOf<Product>()
    val client = OkHttpClient()
    val request = Request.Builder()
            .url(url)
            .build()
    val response = client.newCall(request).execute()
    val responseBody = response.body()!!.string()

    val parser = JsonParser()
    val jsonProducts = parser.parse(responseBody)
    val jsonArray = jsonProducts.getAsJsonArray()
    for (i in 0..(jsonArray.size() - 1)) {
        val item = jsonArray.get(i).asJsonObject
        val name = item["name"].asString
        val available = item["available"].asBoolean
        var link = ""
        if(!item["link"].isJsonNull) {
            link = item["link"].asString
        }
        var image = ""
        if(!item["image"].isJsonNull) {
            image = item["image"].asString
        }
        val history = item["history"].asJsonArray
        val price = history[history.size()-1].asJsonObject["price"].asInt
        val product = Product(name, available, link, price, image)
        products.add(product)

    }
    return products
}