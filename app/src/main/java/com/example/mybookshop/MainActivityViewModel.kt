package com.example.mybookshop

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    var recyclerData:MutableLiveData<List<Book>>

    init {
        recyclerData = MutableLiveData()
    }

    fun getBookListObserverable(): MutableLiveData<List<Book>>{
        return recyclerData
    }

    fun getBookList(page: Int){
        val retroInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
        val call = retroInstance.getBookList(page)
        call.enqueue(object:retrofit2.Callback<List<Book>>{
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                    recyclerData.postValue(response.body())
                    }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                recyclerData.postValue(null)
            }

        })
    }

    fun searchBook(searchText: String?){
        val retroInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
        val call = retroInstance.searchBook(searchText)
        call.enqueue(object:retrofit2.Callback<List<Book>>{
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                recyclerData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                recyclerData.postValue(null)
            }
        })
    }
}