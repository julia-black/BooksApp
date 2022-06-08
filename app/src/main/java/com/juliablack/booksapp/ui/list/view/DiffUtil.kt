package com.juliablack.booksapp.ui.list.view

import androidx.recyclerview.widget.DiffUtil
import com.juliablack.domain.model.Book

val diffUtilsCallback = object : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Book, newItem: Book) = oldItem.id == newItem.id
}