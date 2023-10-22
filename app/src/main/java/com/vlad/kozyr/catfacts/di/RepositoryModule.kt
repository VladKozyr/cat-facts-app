package com.vlad.kozyr.catfacts.di

import com.vlad.kozyr.catfacts.data.CatRepositoryImp
import com.vlad.kozyr.catfacts.data.network.CatFactApi
import com.vlad.kozyr.catfacts.data.network.CatImageApi
import com.vlad.kozyr.catfacts.domain.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCatRepository(catImageApi: CatImageApi, catFactApi: CatFactApi): CatRepository {

        return CatRepositoryImp(catImageApi, catFactApi)
    }
}