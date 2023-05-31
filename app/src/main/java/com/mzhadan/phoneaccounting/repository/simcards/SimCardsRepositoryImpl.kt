package com.mzhadan.phoneaccounting.repository.simcards

import com.mzhadan.phoneaccounting.remote.entities.SimCard
import com.mzhadan.phoneaccounting.remote.sdcards.SdCardsApi
import com.mzhadan.phoneaccounting.remote.simcards.SimCardsApi
import com.mzhadan.phoneaccounting.repository.sdcards.SdCardsRepository
import javax.inject.Inject

class SimCardsRepositoryImpl @Inject constructor(
    private val simCardsApi: SimCardsApi
): SimCardsRepository {
    override suspend fun getAllSimCards(): List<SimCard> =
        simCardsApi.getAllSimCards()

    override suspend fun getSimCardById(simcardId: Int): List<SimCard> =
        simCardsApi.getSimCardById(simcardId)

    override suspend fun getSimCardByNumber(number: String): List<SimCard> =
        simCardsApi.getSimCardByNumber(number)

    override suspend fun addNewSimCard(simcard: SimCard) =
        simCardsApi.addNewSimCard(simcard)

    override suspend fun deleteSimCardById(simcardId: Int) =
        simCardsApi.deleteSimCardById(simcardId)

    override suspend fun updateSimCardIsLocked(simcardId: Int, isLocked: String) =
        simCardsApi.updateSimCardIsLocked(simcardId, isLocked)

}