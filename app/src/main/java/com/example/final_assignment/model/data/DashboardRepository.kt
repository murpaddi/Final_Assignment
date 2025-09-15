package com.example.final_assignment.model.data

import com.example.final_assignment.model.network.RetrofitInstance

class DashboardRepository {
    suspend fun getBooks(): Result<List<Book>> = try {
        val resp = RetrofitInstance.apiService.getDashboard()
        Result.success(resp.entities)
    } catch (e: Exception) {
        Result.failure(e)
    }
}