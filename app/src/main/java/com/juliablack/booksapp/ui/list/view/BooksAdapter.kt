package com.juliablack.booksapp.ui.list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juliablack.booksapp.databinding.ViewBookBinding
import com.juliablack.domain.model.Book

class BooksAdapter(
    private val books: List<Book>,
    private val onClick: (book: Book) -> Unit
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewBookBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ViewBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            books[position].let { item ->
                with(binding) {
                    name.text = item.title
                    root.setOnClickListener {
                        onClick.invoke(item)
                    }
                }
            }
        }
    }

    override fun getItemCount() = books.size
}