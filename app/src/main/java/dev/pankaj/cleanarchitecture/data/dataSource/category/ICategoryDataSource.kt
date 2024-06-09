package dev.pankaj.cleanarchitecture.data.dataSource.category

import dev.pankaj.cleanarchitecture.data.remote.model.LoginRequest
import dev.pankaj.cleanarchitecture.data.remote.model.LoginResponse
import retrofit2.Response

interface ICategoryDataSource {
    suspend fun category(): Response<List<String>>
}