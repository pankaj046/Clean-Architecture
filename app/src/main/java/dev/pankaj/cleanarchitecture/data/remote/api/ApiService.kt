package dev.pankaj.cleanarchitecture.data.remote.api

import dev.pankaj.cleanarchitecture.data.remote.model.auth.LoginRequest
import dev.pankaj.cleanarchitecture.data.remote.model.auth.LoginResponse
import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("products/categories")
    suspend fun category(): Response<List<String>>

    @POST("products")
    suspend fun products(): Response<List<Product>>

}