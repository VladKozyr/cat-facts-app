package com.vlad.kozyr.catfacts.data.network

import com.vlad.kozyr.catfacts.data.model.RandomCatFactsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactApi {

    @GET("/")
    suspend fun getRandomCatFacts(@Query("count") limit: Int): RandomCatFactsResponse
}