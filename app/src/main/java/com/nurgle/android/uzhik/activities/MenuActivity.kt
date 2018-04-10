package com.nurgle.android.uzhik.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MenuActivityUI().setContentView(this@MenuActivity)
    }
}

class MenuActivityUI : AnkoComponent<MenuActivity> {
    override fun createView(ui: AnkoContext<MenuActivity>) = with(ui) {
        verticalLayout {
            button("Search products"){

                onClick {
                    startActivity<SearchActivity>()
                }
            }
            button("Subscriptions"){
                onClick {
                    startActivity<SubscribeActivity>()
                }
            }
        }
    }
}