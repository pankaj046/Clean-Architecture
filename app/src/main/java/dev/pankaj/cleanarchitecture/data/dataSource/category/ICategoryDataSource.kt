package dev.pankaj.cleanarchitecture.data.dataSource.category

import retrofit2.Response

interface ICategoryDataSource {
    suspend fun category(): Response<List<String>>
}