package com.example.final_assignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_assignment.model.data.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    // emits the keypass on success. null is no success yet.
    private val _keypassState = MutableStateFlow<String?>(null)
    val keypassState: StateFlow<String?> = _keypassState

    // emits an error message. or null to clear
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    //simple loading flag
    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    fun clearError() {_errorState.value = null}
    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _errorState.value = "Please enter both username and password."
            return
        }
        _loadingState.value = true
        _errorState.value = null

        viewModelScope.launch {
            val result = repository.login(username, password)
            result.onSuccess { key -> _keypassState.value = key }
            result.onFailure { e -> _errorState.value = e.message ?: "Login failed." }
            _loadingState.value=false
        }
    }
}

