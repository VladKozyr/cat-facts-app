package com.vlad.kozyr.catfacts.data

import com.vlad.kozyr.catfacts.data.network.CatFactApi
import com.vlad.kozyr.catfacts.data.network.CatImageApi
import com.vlad.kozyr.catfacts.domain.CatRepository
import com.vlad.kozyr.catfacts.domain.model.CatFact
import com.vlad.kozyr.catfacts.domain.model.CatImage
import javax.inject.Inject

class CatRepositoryImp @Inject constructor(
    private val catImageApi: CatImageApi,
    private val catFactApi: CatFactApi
) : CatRepository {
    override suspend fun getRandomCatImages(quantity: Int): List<CatImage> {
        return catImageApi.getRandomCatImages(quantity)
            .map { CatImage(it.url) }
    }

    override suspend fun getRandomCatFacts(quantity: Int): List<CatFact> {
        return catFactApi.getRandomCatFacts(quantity).data
            .map { CatFact(it) }
    }
}