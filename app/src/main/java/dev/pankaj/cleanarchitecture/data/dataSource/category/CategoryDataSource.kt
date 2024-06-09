package dev.pankaj.cleanarchitecture.data.dataSource.category

import dev.pankaj.cleanarchitecture.data.remote.api.ApiService
import retrofit2.Response
import javax.inject.Inject

class CategoryDataSource @Inject constructor(private val api: ApiService) : ICategoryDataSource {

    override suspend fun category(): Response<List<String>> {
        return api.category()
    }
}