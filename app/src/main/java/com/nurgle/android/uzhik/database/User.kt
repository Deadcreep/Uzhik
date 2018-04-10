package com.nurgle.android.uzhik.database

import android.arch.persistence.room.*
import android.util.Log
import com.nurgle.android.uzhik.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



@Entity(tableName = "users")
data class User(
        var name: String = "",
        var token: String = "",
        var password: String = ""
){
    @PrimaryKey(autoGenerate = true)
    var uid: Long? = null
}

@Dao
interface UserDAO {

    @Query("SELECT * FROM users")
    fun getAllPeople(): List<User>

    @Query("SELECT * FROM users WHERE name =:name")
    fun getUser(name: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: User)

    @Query("UPDATE users SET token=:token WHERE name = :name")
    fun updateToken(token : String, name: String);

    @Query("UPDATE users SET password=:password WHERE name = :name")
    fun updatePassword(password : String, name: String);


    @Query("Select token From users WHERE name = :name")
    fun getTokenByName(name : String) : String

    @Query("SELECT * FROM users WHERE uid = (SELECT MIN(uid) FROM users);")
    fun getLastUser() : User
}

fun insertUserInDb( user : User) {
    try {
        val tempUser = App.database?.userDAO()!!.getUser(user.name)
        if(user.name == tempUser.name
                && user.password == tempUser.password
                && user.token == tempUser.token){
            Log.i("insertUserInDb", "User exists")
        }
        else if(user.name == tempUser.name
                && user.password == tempUser.password
                && user.token != tempUser.token){
            App.database?.userDAO()!!.updateToken(user.token, user.name)
            Log.i("insertUserInDb", "token updated")
        }
        else if(user.name == tempUser.name
                && user.password != tempUser.password
                && user.token == tempUser.token){
            App.database?.userDAO()!!.updatePassword(user.password, user.name)
            Log.i("insertUserInDb", "password updated")
        }
    }
    catch (e: NullPointerException){
        App.database?.userDAO()!!.insert(user)
        Log.i("insertUserInDb", "User inserted")
    }
}

