package com.example.mybookshop
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val clickListener:OnItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var bookList = mutableListOf<Book>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_list,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(bookList[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemEditClick(bookList[position])
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        val textViewName = view.findViewById<TextView>(R.id.textViewBookName)
        val textviewAuthor = view.findViewById<TextView>(R.id.textViewAuthor)
        val textviewDate = view.findViewById<TextView>(R.id.textviewDate)

        fun bind(data: Book){
            textViewName.text = data.title
            textviewAuthor.text = data.author
            textviewDate.text = data.publicationDate
        }
    }

    interface OnItemClickListener{
        fun onItemEditClick(book:Book)
    }
}