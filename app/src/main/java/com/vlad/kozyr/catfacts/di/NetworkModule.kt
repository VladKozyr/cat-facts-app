package com.vlad.kozyr.catfacts.di

import com.vlad.kozyr.catfacts.data.network.CatFactApi
import com.vlad.kozyr.catfacts.data.network.CatImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRandomCatImageApi(): CatImageApi {

        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CatImageApi::class.java)

    }

    @Provides
    fun provideRandomCatFactApi(): CatFactApi {

        return Retrofit.Builder()
            .baseUrl("https://meowfacts.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CatFactApi::class.java)

    }
}