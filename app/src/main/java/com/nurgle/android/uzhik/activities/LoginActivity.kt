package com.nurgle.android.uzhik.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType.*
import android.util.Log
import com.nurgle.android.uzhik.App
import com.nurgle.android.uzhik.network.checkLogin
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.lang.Exception


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("onCreate", "login activity created")
        LoginActivityUI().setContentView(this@LoginActivity)

    }

    fun tryLogin(name: String, password: String) {
        var result = checkLogin(name, password)
        runOnUiThread {
            if (result != "Invalid username or password.") {
                toast("logged in")
                startActivity<MenuActivity>()
            } else {
                toast("Wrong password or login")
            }
        }
    }
}


class LoginActivityUI : AnkoComponent<LoginActivity> {
    var name = ""
    var password = ""
    override fun createView(ui: AnkoContext<LoginActivity>) = with(ui) {
        try{
            val lastUser  = App.database?.userDAO()!!.getLastUser()
            name = lastUser.name
            password = lastUser.password
        }
        catch (e : Exception){

        }
        verticalLayout{
            val name = editText(){
                hint = name
            }
            val password = editText(){
                inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
                hint = password
            }
            button("Login") {
                onClick {
                    doAsync {
                        ui.owner.tryLogin(name.text.toString(), password.text.toString())
                    }
                    text = "Please wait"
                }
            }
        }
    }
}