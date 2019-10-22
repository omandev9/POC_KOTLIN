package com.example.kotlinrecyclerview.model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.kotlinrecyclerview.DatabaseUsers
import com.example.kotlinrecyclerview.UserDao

class WordRepository internal constructor(application: Application) {
    private val wordDao: UserDao
//    internal val allWords: LiveData<List<User>>
    internal val allWords: LiveData<List<User>>

    init {
        val db = DatabaseUsers.getDatabase(application)
        wordDao = db!!.userDao()
        allWords = wordDao.getAllUsers()
    }

    fun insert(word: User) {
        InsertAsyncTask(wordDao).execute()
    }

    inner class InsertAsyncTask internal constructor(var mAsyncWordDao: UserDao) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg words: User): Void? {
            mAsyncWordDao.insert(words[0])
            return null
        }
    }

}
