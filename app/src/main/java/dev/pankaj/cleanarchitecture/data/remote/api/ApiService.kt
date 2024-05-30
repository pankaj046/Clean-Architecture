package dev.pankaj.cleanarchitecture.data.remote.api

import dev.pankaj.cleanarchitecture.data.remote.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<String>

}