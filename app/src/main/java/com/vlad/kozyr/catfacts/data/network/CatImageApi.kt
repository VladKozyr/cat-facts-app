package com.vlad.kozyr.catfacts.data.network

import com.vlad.kozyr.catfacts.data.model.RandomCatImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CatImageApi {

    @GET("/v1/images/search")
    suspend fun getRandomCatImages(@Query("limit") limit: Int): List<RandomCatImageResponse>
}