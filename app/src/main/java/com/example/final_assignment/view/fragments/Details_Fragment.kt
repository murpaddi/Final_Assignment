package com.example.final_assignment.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.final_assignment.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Details_Fragment : Fragment() {

    private val args: Details_FragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvAuthor: TextView = view.findViewById(R.id.tvAuthor)
        val tvMeta: TextView = view.findViewById(R.id.tvMeta)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)

        val book = args.book
        tvTitle.text = book.title
        tvAuthor.text = "by ${book.author}"
        tvMeta.text = "${book.genre} - ${book.publicationYear}"
        tvDescription.text = book.description
    }
}