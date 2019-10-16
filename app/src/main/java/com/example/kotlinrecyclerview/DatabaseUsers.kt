package com.example.kotlinrecyclerview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinrecyclerview.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class DatabaseUsers : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: DatabaseUsers? = null
        fun getDatabase(context: Context): DatabaseUsers? {
            if (INSTANCE == null) {
                synchronized(DatabaseUsers::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        DatabaseUsers::class.java, "users.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}