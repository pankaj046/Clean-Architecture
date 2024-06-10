package dev.pankaj.cleanarchitecture.data.dataSource.product

import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import retrofit2.Response

interface IProductDataSource {
    suspend fun product(): Response<List<Product>>
    suspend fun getProduct(productId: Int): Response<Product>
}