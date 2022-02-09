package com.example.mybookshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Response

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val lbID:TextView = findViewById<TextView>(R.id.lb_id)
        val txtIsbn:EditText = findViewById<EditText>(R.id.txt_isbn)
        val txtTitle:EditText = findViewById<EditText>(R.id.txt_title)
        val txtDescription:EditText = findViewById<EditText>(R.id.txt_Description)
        val txtPubDate:EditText = findViewById<EditText>(R.id.txt_pubDate)

        val bookIDPath:String? = intent.getStringExtra("id").toString()
        val bookID:String? = bookIDPath?.substringAfter("/books/")

        val retroInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
        val call = retroInstance.searchBook(bookID)

        var book:Book?

        call.enqueue(object:retrofit2.Callback<Book>{
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                book = response.body()
                lbID.text = "ID: " + bookID
                txtIsbn.setText("ISBN: " + book?.isbn)
                txtTitle.setText("TITLE: " + book?.title)
                txtDescription.setText("DESCRIPTION: " + book?.description)
                txtPubDate.setText("PUBLICATION DATE: " + book?.publicationDate)
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
                Log.i("Tag", "not found!")
            }
        })
    }
}