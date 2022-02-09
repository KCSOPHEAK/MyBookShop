package com.example.mybookshop

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("books")
    @Headers("accept: application/ld+json","Content-Type:application/json")
    fun getBookList(@Query("page")pageNumber:Int):Call<BookList>

    @GET("books/{id}")
    @Headers("accept:application/json","Content-Type:application/json")
    fun searchBook(@Path("id")searchID: String?):Call<Book>
}