package dev.pankaj.cleanarchitecture.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.pankaj.cleanarchitecture.App
import dev.pankaj.cleanarchitecture.domain.usecase.ProductUseCase


class ProductViewModelFactory(
    private val app: App,
    private val productUseCase: ProductUseCase,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(
            app,
            productUseCase
        ) as T
    }
}