package com.nurgle.android.uzhik.network

import com.nurgle.android.uzhik.database.*
import okhttp3.*


fun checkLogin(name: String, password: String): String {
    val url = "http://88.206.52.198:7575/token"
    val client = OkHttpClient()
    val body = FormBody.Builder().add("username", name).add("password", password).build()
    val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build()

    val response = client.newCall(request).execute()
    val result = response.body()!!.string()
    if (result != "Invalid username or password."){
       insertUserInDb(User(name = name, password = password, token = result))
    }
    return result
}


