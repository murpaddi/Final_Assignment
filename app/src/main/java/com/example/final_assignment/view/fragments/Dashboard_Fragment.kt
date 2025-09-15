package com.example.final_assignment.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_assignment.R
import com.example.final_assignment.model.data.Book
import com.example.final_assignment.view.adapter.BookAdapter
import com.example.final_assignment.viewModel.DashboardViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Dashboard_Fragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: BookAdapter
    private lateinit var rvItems: RecyclerView
    private lateinit var tvHeader: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvItems = view.findViewById(R.id.rvItems)
        tvHeader = view.findViewById(R.id.tvBooksDashboard)

        adapter = BookAdapter { book ->
            val action = Dashboard_FragmentDirections
                .actionDashboardFragmentToDetailsFragment(book)
            findNavController().navigate(action)
        }

        rvItems.layoutManager = LinearLayoutManager(requireContext())
        rvItems.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.books.collectLatest { list ->
                adapter.submitList(list)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collectLatest { error ->
                if (error != null) {
                    tvHeader.text = "Error: $error"
                } else {
                    tvHeader.text = "Dashboard"
                }
            }
        }

        viewModel.loadBooks()
    }
}