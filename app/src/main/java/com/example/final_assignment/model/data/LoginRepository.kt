package com.example.final_assignment.model.data

import com.example.final_assignment.model.network.ApiService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun login(username: String, password: String): Result<String> = try {
        val resp = api.login(LoginRequest(username, password))
        if (!resp.keypass.isNullOrBlank()) Result.success(resp.keypass)
        else Result.failure(IllegalStateException("Missing Keypass"))
    } catch (e: retrofit2.HttpException) {
        val msg = if (e.code() == 400 || e.code() == 401) "Invalid Username or Password."
        else "Login failed (code ${e.code()})."
        Result.failure(Exception(msg))
    } catch (e: java.io.IOException) {
        Result.failure(Exception("Network error. Try again."))
    } catch (e: Exception) {
        Result.failure(Exception("Unexpected error: ${e.message ?: "Unknown"}"))
    }

}