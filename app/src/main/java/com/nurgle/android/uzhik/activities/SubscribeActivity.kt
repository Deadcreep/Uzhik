package com.nurgle.android.uzhik.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.nurgle.android.uzhik.App
import com.nurgle.android.uzhik.ProductAdapter
import com.nurgle.android.uzhik.database.Product
import com.nurgle.android.uzhik.network.getSubscribtions
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView


class SubscribeActivity : AppCompatActivity() {
    private val products = mutableListOf<Product>()
    private val productAdapter = ProductAdapter(products)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doAsync {
            products.addAll(getSubscribtions(App.database?.userDAO()!!.getLastUser().token))
        }
        Log.i("ACTIVITY CREATED", products.size.toString())
        SubscribeActivityUI(productAdapter).setContentView(this)
    }
}

class SubscribeActivityUI(val productAdapter: ProductAdapter) : AnkoComponent<SubscribeActivity> {
    override fun createView(ui: AnkoContext<SubscribeActivity>): View = with(ui) {
        return relativeLayout {
            val list = recyclerView {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
                overScrollMode = View.OVER_SCROLL_NEVER
                adapter = productAdapter
            }.lparams(width = matchParent, height = wrapContent) {
            }
        }.apply{
            Log.i("UI CREATED", this@SubscribeActivityUI.toString())
        }
    }
}
