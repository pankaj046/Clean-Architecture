package dev.pankaj.cleanarchitecture.presentation.home.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import dev.pankaj.cleanarchitecture.App
import dev.pankaj.cleanarchitecture.data.remote.model.product.Product
import dev.pankaj.cleanarchitecture.domain.usecase.ProductUseCase
import dev.pankaj.cleanarchitecture.utils.CallBack
import dev.pankaj.cleanarchitecture.utils.NetworkUtils
import kotlinx.coroutines.launch

@ViewModelScoped
class ProductViewModel(
    private val app: App,
    private val productUseCase: ProductUseCase,
) : AndroidViewModel(app) {

    private val _productListResponse: MutableLiveData<CallBack<List<Product>>> = MutableLiveData()
    val productListResponse: LiveData<CallBack<List<Product>>> = _productListResponse

    private val _productResponse: MutableLiveData<CallBack<Product>> = MutableLiveData()
    val productResponse: LiveData<CallBack<Product>> = _productResponse


    fun productList() = viewModelScope.launch {
        _productListResponse.value = CallBack.Loading(true)
        if (NetworkUtils.isNetworkAvailable(app)){
            productUseCase.productList().apply {
                _productListResponse.value = this
                _productListResponse.value = CallBack.Loading(false)
            }
        }else {
            _productListResponse.value = CallBack.Message("No Internet connection")
            _productListResponse.value = CallBack.Loading(false)
        }
    }

    fun product(productId: Int) = viewModelScope.launch {
        _productResponse.value = CallBack.Loading(true)
        if (NetworkUtils.isNetworkAvailable(app)){
            productUseCase.getProduct(productId).apply {
                _productResponse.value = this
                _productResponse.value = CallBack.Loading(false)
            }
        }else {
            _productResponse.value = CallBack.Message("No Internet connection")
            _productResponse.value = CallBack.Loading(false)
        }
    }
}