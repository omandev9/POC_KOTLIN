package com.example.kotlinrecyclerview.activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.kotlinrecyclerview.APIService
import com.example.kotlinrecyclerview.R
import com.example.kotlinrecyclerview.RestClient
import com.example.kotlinrecyclerview.model.UserList
import com.example.kotlinrecyclerview.adapters.ListAdapterForRetrofit
import com.example.kotlinrecyclerview.adapters.WordListAdapter
import com.example.kotlinrecyclerview.model.User
import com.example.kotlinrecyclerview.viewModel.WordViewModel
import kotlinx.android.synthetic.main.activity_main_for_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityForRetrofit : AppCompatActivity() {

    private var mApiService: APIService? = null
    private var mAdapter: ListAdapterForRetrofit?= null
    private var mQuestions: MutableList<User> = ArrayList()
    private var mWordViewModel: WordViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_for_retrofit)

        val recyclerView = findViewById<RecyclerView>(R.id.listRecyclerView)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)


        mWordViewModel!!.getAllWords().observe(this, Observer<List<User>?> {
            fun onChanged(words: List<User>) {
                adapter.setWords(words)
            }
        })

        mApiService = RestClient.client.create(APIService::class.java)

        listRecyclerView!!.layoutManager = LinearLayoutManager(this)

        mAdapter = ListAdapterForRetrofit(this, mQuestions, R.layout.question_item)
        listRecyclerView!!.adapter = mAdapter

        // todo : later need to remove this call from here
        fetchQuestionList()
    }

    private fun fetchQuestionList() {
        val call = mApiService!!.fetchQuestions("android")
        call.enqueue(object : Callback<UserList> {

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {

                Log.d("Logs===", "Total Questions: " + response.body()!!.items!!.size)
                val questions = response.body()
                if (questions != null) {
                    mQuestions.addAll(questions.items!!)
                    mAdapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                Log.e("Logs===", "Got error : " + t.localizedMessage)
            }
        })
    }
}