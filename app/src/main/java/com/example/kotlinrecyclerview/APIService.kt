package com.example.kotlinrecyclerview

import com.example.kotlinrecyclerview.model.UserList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")    //End Url
    fun fetchQuestions(@Query("tagged") tags: String): Call<UserList>
}