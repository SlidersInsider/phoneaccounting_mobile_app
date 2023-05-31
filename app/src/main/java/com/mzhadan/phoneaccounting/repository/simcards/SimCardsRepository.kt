package com.mzhadan.phoneaccounting.repository.simcards

import com.mzhadan.phoneaccounting.remote.entities.SimCard
import retrofit2.http.*

interface SimCardsRepository {
    suspend fun getAllSimCards(): List<SimCard>
    suspend fun getSimCardById(simcardId: Int): List<SimCard>
    suspend fun getSimCardByNumber(number: String): List<SimCard>
    suspend fun addNewSimCard(simcard: SimCard)
    suspend fun deleteSimCardById(simcardId: Int)
    suspend fun updateSimCardIsLocked(simcardId: Int, isLocked: String)
}