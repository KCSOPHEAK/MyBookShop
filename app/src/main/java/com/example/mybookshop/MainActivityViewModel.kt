package com.example.mybookshop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    var recyclerData:MutableLiveData<BookList>

    init {
        recyclerData = MutableLiveData()
    }

    fun getBookListObserverable(): MutableLiveData<BookList> {
        return recyclerData
    }

    fun getBookList(page: Int){
        val retroInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
        val call = retroInstance.getBookList(page)
        call.enqueue(object:retrofit2.Callback<BookList>{
            override fun onResponse(call: Call<BookList>, response: Response<BookList>) {
                    recyclerData.postValue(response.body())
                    }

            override fun onFailure(call: Call<BookList>, t: Throwable) {
                recyclerData.postValue(null)
            }

        })
    }
}