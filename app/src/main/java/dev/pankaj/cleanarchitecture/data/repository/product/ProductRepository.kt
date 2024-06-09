package dev.pankaj.cleanarchitecture.data.repository.product

import dev.pankaj.cleanarchitecture.data.dataSource.product.IProductDataSource
import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import dev.pankaj.cleanarchitecture.domain.repository.IProductRepository
import dev.pankaj.cleanarchitecture.utils.*

class ProductRepository(
    private val productDataSource: IProductDataSource,
) : IProductRepository {

    override suspend fun product(): CallBack<List<Product>> {
        return try {
            val response = productDataSource.product()
            if (response.isSuccessful) {
                response.body()?.let {
                    CallBack.Success(it)
                } ?: CallBack.Message("Response body is null")
            } else {
                CallBack.Message("Product failed with code: ${response.code()}")
            }
        } catch (e: Exception) {
            CallBack.Error(e)
        }
    }
}