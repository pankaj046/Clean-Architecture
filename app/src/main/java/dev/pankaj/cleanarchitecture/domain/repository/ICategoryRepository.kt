package dev.pankaj.cleanarchitecture.domain.repository

import dev.pankaj.cleanarchitecture.utils.*

interface ICategoryRepository {
    suspend fun category(): CallBack<List<String>>
}