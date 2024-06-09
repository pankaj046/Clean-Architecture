package dev.pankaj.cleanarchitecture.domain.repository

import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import dev.pankaj.cleanarchitecture.utils.*

interface IProductRepository {
    suspend fun product(): CallBack<List<Product>>
}