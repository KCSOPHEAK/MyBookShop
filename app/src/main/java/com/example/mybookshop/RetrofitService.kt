package com.example.mybookshop

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("books")
    @Headers("accept:application/json","Content-Type:application/json")
    fun getBookList(@Query("page")pageNumber:Int):Call<List<Book>>

    @GET("books")
    @Headers("accept:application/json","Content-Type:application/json")
    fun searchBook(@Query("id")searchText: String?):Call<List<Book>>
}