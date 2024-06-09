package dev.pankaj.cleanarchitecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pankaj.cleanarchitecture.data.dataSource.auth.IAuthDataSource
import dev.pankaj.cleanarchitecture.data.dataSource.category.ICategoryDataSource
import dev.pankaj.cleanarchitecture.data.dataSource.product.IProductDataSource
import dev.pankaj.cleanarchitecture.data.dataSource.user.IUserLocalDataSource
import dev.pankaj.cleanarchitecture.data.local.prefmanager.SharedPreferencesUtil
import dev.pankaj.cleanarchitecture.data.repository.auth.AuthRepository
import dev.pankaj.cleanarchitecture.data.repository.category.CategoryRepository
import dev.pankaj.cleanarchitecture.data.repository.product.ProductRepository
import dev.pankaj.cleanarchitecture.data.repository.user.UserRepository
import dev.pankaj.cleanarchitecture.domain.repository.IAuthRepository
import dev.pankaj.cleanarchitecture.domain.repository.ICategoryRepository
import dev.pankaj.cleanarchitecture.domain.repository.IProductRepository
import dev.pankaj.cleanarchitecture.domain.repository.IUserRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideUserRepository(
        userLocalDataSource: IUserLocalDataSource
    ): IUserRepository {
        return UserRepository(
            userLocalDataSource
        )
    }

    @Provides
    fun provideAuthRepository(
        authDataSource: IAuthDataSource,
        sharedPreferencesUtil: SharedPreferencesUtil
    ): IAuthRepository {
        return AuthRepository(
            authDataSource,
            sharedPreferencesUtil
        )
    }

    @Provides
    fun provideProductRepository(
        productDataSource: IProductDataSource,
    ): IProductRepository {
        return ProductRepository(
            productDataSource
        )
    }

    @Provides
    fun provideCategoryRepository(
        categoryDataSource: ICategoryDataSource,
    ): ICategoryRepository {
        return CategoryRepository(
            categoryDataSource
        )
    }

}