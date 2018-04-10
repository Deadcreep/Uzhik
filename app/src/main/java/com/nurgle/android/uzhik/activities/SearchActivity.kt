package com.nurgle.android.uzhik.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.nurgle.android.uzhik.App
import com.nurgle.android.uzhik.ProductAdapter
import com.nurgle.android.uzhik.database.Product
import com.nurgle.android.uzhik.network.findProducts
import com.nurgle.android.uzhik.network.getSubscribtions
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick

class SearchActivity : AppCompatActivity() {
    private val products = mutableListOf<Product>()
    private val productAdapter = ProductAdapter(products)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("ACTIVITY CREATED", products.size.toString())
        SearchActivityUI(productAdapter).setContentView(this)
    }
    fun search(name: String){
        doAsync {
            products.addAll(findProducts(name))
        }
        productAdapter.notifyDataSetChanged()
    }
}


class SearchActivityUI(val productAdapter: ProductAdapter) : AnkoComponent<SearchActivity> {
    override fun createView(ui: AnkoContext<SearchActivity>): View = with(ui) {
        return verticalLayout() {
            val findText = editText(){
            }
            val searchButton = button("Search"){
                onClick {
                    ui.owner.search(findText.text.toString())
                }
            }
            val list = recyclerView {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
                overScrollMode = View.OVER_SCROLL_NEVER
                adapter = productAdapter
            }.lparams(width = matchParent, height = wrapContent) {
            }
        }.apply{
        }
    }
}
