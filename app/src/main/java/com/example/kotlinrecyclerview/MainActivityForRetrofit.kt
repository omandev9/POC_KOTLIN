package com.example.kotlinrecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.kotlinrecyclerview.adapter.ListAdapterForRetrofit
import com.example.kotlinrecyclerview.model.User
import kotlinx.android.synthetic.main.activity_main_for_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivityForRetrofit : AppCompatActivity() {

    private var mApiService: APIService? = null

    private var mAdapter: ListAdapterForRetrofit?= null;
    private var mQuestions: MutableList<User> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_for_retrofit)

        mApiService = RestClient.client.create(APIService::class.java)

        listRecyclerView!!.layoutManager = LinearLayoutManager(this)

        mAdapter = ListAdapterForRetrofit(this, mQuestions, R.layout.question_item)
        listRecyclerView!!.adapter = mAdapter

        fetchQuestionList()
    }

    private fun fetchQuestionList() {
        val call = mApiService!!.fetchQuestions("android");
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