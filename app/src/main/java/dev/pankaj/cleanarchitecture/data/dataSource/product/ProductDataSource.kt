package dev.pankaj.cleanarchitecture.data.dataSource.product

import dev.pankaj.cleanarchitecture.data.remote.api.ApiService
import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import retrofit2.Response
import javax.inject.Inject

class ProductDataSource @Inject constructor(private val api: ApiService) : IProductDataSource {

    override suspend fun product(): Response<List<Product>> {
        return api.products()
    }

    override suspend fun getProduct(productId: Int): Response<Product> {
        return api.product(productId)
    }
}