package com.vlad.kozyr.catfacts.domain

import com.vlad.kozyr.catfacts.domain.model.CatFact
import com.vlad.kozyr.catfacts.domain.model.CatImage

interface CatRepository {

    suspend fun getRandomCatImages(quantity: Int): List<CatImage>

    suspend fun getRandomCatFacts(quantity: Int): List<CatFact>
}