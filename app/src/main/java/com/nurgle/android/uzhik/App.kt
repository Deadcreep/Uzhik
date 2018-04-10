package com.nurgle.android.uzhik

import android.app.Application
import android.arch.persistence.room.Room
import android.util.Log
import com.nurgle.android.uzhik.database.UzhikDatabase

class App : Application() {

    companion object {
        var database: UzhikDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(this, UzhikDatabase::class.java, "uzhikdb").build()
    }
}