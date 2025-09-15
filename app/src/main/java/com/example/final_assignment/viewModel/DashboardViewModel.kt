package com.example.final_assignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_assignment.model.data.Book
import com.example.final_assignment.model.data.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val repo = DashboardRepository()

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadBooks() {
        viewModelScope.launch {
            val result = repo.getBooks()
            result.fold(
                onSuccess = { _books.value = it},
                onFailure = { _error.value = it.message }
            )
        }
    }
}