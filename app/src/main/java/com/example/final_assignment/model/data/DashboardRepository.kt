package com.example.final_assignment.model.data

import com.example.final_assignment.model.network.ApiService
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getBooks(): Result<List<Book>> = try {
        val resp = api.getDashboard()
        Result.success(resp.entities)
    } catch (e: Exception) {
        Result.failure(e)
    }
}