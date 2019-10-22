package com.example.kotlinrecyclerview.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.kotlinrecyclerview.model.User
import com.example.kotlinrecyclerview.model.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: WordRepository

    internal val allWords: LiveData<List<User>>

    init {
        mRepository = WordRepository(application)
        allWords = mRepository.allWords
    }

    fun insert(word: User) {
        mRepository.insert(word)
    }

    fun getAllWords(): LiveData<List<User>> {
        return allWords
    }
}