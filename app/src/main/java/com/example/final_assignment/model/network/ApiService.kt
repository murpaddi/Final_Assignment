package com.example.final_assignment.model.network

import com.example.final_assignment.model.data.DashboardResponse
import com.example.final_assignment.model.data.LoginRequest
import com.example.final_assignment.model.data.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/footscray/auth")
    suspend fun login(@Body body: LoginRequest): LoginResponse

    @GET("dashboard/books")
    suspend fun getDashboard(): DashboardResponse
}