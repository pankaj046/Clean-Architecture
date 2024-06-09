package dev.pankaj.cleanarchitecture.data.repository.category

import dev.pankaj.cleanarchitecture.data.dataSource.category.ICategoryDataSource
import dev.pankaj.cleanarchitecture.domain.repository.ICategoryRepository
import dev.pankaj.cleanarchitecture.utils.*

class CategoryRepository(
    private val categoryDataSource: ICategoryDataSource,
) : ICategoryRepository {

    override suspend fun category(): CallBack<List<String>> {
        return try {
            val response = categoryDataSource.category()
            if (response.isSuccessful) {
                response.body()?.let {
                    CallBack.Success(it)
                } ?: CallBack.Message("Response body is null")
            } else {
                CallBack.Message("Category failed with code: ${response.code()}")
            }
        } catch (e: Exception) {
            CallBack.Error(e)
        }
    }
}