package com.aidul23.rxjavatutorial.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aidul23.rxjavatutorial.R
import com.aidul23.rxjavatutorial.model.BookList
import com.aidul23.rxjavatutorial.model.VolumeInfo
import com.bumptech.glide.Glide
import java.util.ArrayList

class BookListAdapter: RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    var bookList = ArrayList<VolumeInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.BookViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return BookViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: BookListAdapter.BookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class BookViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvPublisher: TextView = view.findViewById(R.id.tvPublisher)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val image: ImageView = view.findViewById(R.id.thumbImageView)

            fun bind(data: VolumeInfo) {
                tvTitle.text = data.volumeInfo.title
                tvPublisher.text = data.volumeInfo.publisher
                tvDescription.text = data.volumeInfo.description

                val url = data .volumeInfo?.imageLinks.smallThumbnail
                Glide.with(image)
                    .load(url)
                    .centerCrop()
                    .into(image)
            }
    }


}