package dev.pankaj.cleanarchitecture.domain.usecase

import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import dev.pankaj.cleanarchitecture.domain.repository.IProductRepository
import dev.pankaj.cleanarchitecture.utils.CallBack

class ProductUseCase(private val iProductRepository: IProductRepository){

    suspend fun productList(): CallBack<List<Product>>{
        return iProductRepository.product()
    }


    suspend fun getProduct(productId: Int): CallBack<Product>{
        return iProductRepository.getProduct(productId)
    }
}
