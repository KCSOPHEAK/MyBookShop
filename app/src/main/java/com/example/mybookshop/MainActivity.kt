package com.example.mybookshop

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    var page = 1
    var max_page = 0
    var isLoading = false

    var viewModel: MainActivityViewModel = MainActivityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycleView: RecyclerView = findViewById(R.id.recycleView)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)

        initRecyclerView(recycleView)

        recycleView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recycleView.layoutManager as LinearLayoutManager
                    val pastVisibleItem:Int = layoutManager.findLastCompletelyVisibleItemPosition()
                    val total: Int = recyclerViewAdapter.itemCount

                    if ((pastVisibleItem + 1  == total) && !isLoading){
                            page ++
                            loadMore(page, progressBar)
                    }

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun initRecyclerView(recyclerView:RecyclerView){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
        }
        initViewModel(1)
    }

    fun loadMore(page: Int, progressBar: ProgressBar){
        isLoading = true
        progressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            initViewModel(page)
            isLoading = false
            progressBar.visibility=View.GONE
        },1000)
    }

    private fun initViewModel(page:Int){
                viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
                viewModel.getBookList(page)
                viewModel.getBookListObserverable().observe(this, Observer<BookList> {
                    if (it == null) {
                        Toast.makeText(this@MainActivity, "no result found!", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        if (page > max_page && it.data.toMutableList().size > 0) {
                            max_page = page
                            recyclerViewAdapter.bookList.addAll(it.data.toMutableList())
                            recyclerViewAdapter.notifyDataSetChanged()
                        }
                    }
                })

    }

    override fun onItemEditClick(book: Book) {
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra("id", book.id)
        startActivity(intent)
    }

}