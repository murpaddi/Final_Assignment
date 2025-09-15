package com.example.final_assignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final_assignment.model.data.Book
import com.example.final_assignment.R

class BookAdapter(
    private val onClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var items: List<Book> = emptyList()

    fun submitList(list: List<Book>) {
        items = list
        notifyDataSetChanged()
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvMeta: TextView = itemView.findViewById(R.id.tvMeta)
        private val btnDetails: Button = itemView.findViewById(R.id.btnDetails)

        fun bind(item: Book) {
            tvTitle.text = item.title
            tvAuthor.text = item.author
            tvMeta.text = "${item.genre}, ${item.publicationYear}"

            btnDetails.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}