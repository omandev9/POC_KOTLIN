package com.example.kotlinrecyclerview

import android.arch.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinrecyclerview.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Query("SELECT * FROM UserTable")
    fun  getAllUsers(): LiveData<List<User>>

}