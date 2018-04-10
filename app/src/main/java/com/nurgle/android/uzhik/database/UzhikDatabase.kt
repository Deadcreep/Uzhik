package com.nurgle.android.uzhik.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = arrayOf(User::class, Product::class), version = 1)
abstract class UzhikDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun productDAO(): ProductDAO
}