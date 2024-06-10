package dev.pankaj.cleanarchitecture.data.remote.api

import dev.pankaj.cleanarchitecture.data.remote.model.auth.LoginRequest
import dev.pankaj.cleanarchitecture.data.remote.model.auth.LoginResponse
import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("products/categories")
    suspend fun category(): Response<List<String>>

    @GET("products")
    suspend fun products(): Response<List<Product>>

    @GET("products/{id}")
    suspend fun product(@Path("id") productId:Int) :Response<Product>

}