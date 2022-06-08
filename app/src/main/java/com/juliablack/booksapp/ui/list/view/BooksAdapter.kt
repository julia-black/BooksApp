package com.juliablack.booksapp.ui.list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juliablack.booksapp.databinding.ViewBookBinding
import com.juliablack.domain.model.Book

class BooksAdapter(
    private val onClick: (book: Book) -> Unit
) : PagingDataAdapter<Book, BooksAdapter.ViewHolder>(diffUtilsCallback) {

    inner class ViewHolder(val binding: ViewBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ViewBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            with(holder.binding) {
                name.text = item.title
                root.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }
}