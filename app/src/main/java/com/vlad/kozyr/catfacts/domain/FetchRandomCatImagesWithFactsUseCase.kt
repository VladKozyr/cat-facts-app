package com.vlad.kozyr.catfacts.domain

import com.vlad.kozyr.catfacts.domain.model.CatImageWithFact
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Integer.min
import javax.inject.Inject

class FetchRandomCatImagesWithFactsUseCase @Inject constructor(
    private val catRepository: CatRepository,
) {

    suspend operator fun invoke(): List<CatImageWithFact> = coroutineScope {
        val imagesDef = async { catRepository.getRandomCatImages(LOAD_SIZE) }
        val factsDef = async { catRepository.getRandomCatFacts(LOAD_SIZE) }

        val images = imagesDef.await()
        val facts = factsDef.await()

        List(min(images.size, facts.size)) { index ->
            CatImageWithFact(
                images[index].url,
                facts[index].fact
            )
        }
    }

    companion object {

        private const val LOAD_SIZE = 5
    }
}