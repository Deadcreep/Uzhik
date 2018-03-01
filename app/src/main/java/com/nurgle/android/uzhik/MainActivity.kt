package com.nurgle.android.uzhik

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.*
import okhttp3.*
import com.google.gson.Gson
import android.support.v7.widget.RecyclerView
import android.widget.Adapter
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private val products = mutableListOf<String>()
    private val productAdapter = ProductAdapter(products)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for(i in 1..20){
            products.add( i.toString())
        }
        val recyclerView = recyclerView(){
            adapter = productAdapter

            layoutManager = LinearLayoutManager(this@MainActivity)

        }
        setContentView(recyclerView)
    }
}
