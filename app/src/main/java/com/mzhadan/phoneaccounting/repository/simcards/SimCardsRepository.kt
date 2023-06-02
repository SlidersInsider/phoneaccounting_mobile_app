package com.mzhadan.phoneaccounting.repository.simcards

import com.mzhadan.phoneaccounting.remote.entities.Locked
import com.mzhadan.phoneaccounting.remote.entities.SimCard
import retrofit2.Response
import retrofit2.http.*

interface SimCardsRepository {
    suspend fun getAllSimCards(): List<SimCard>
    suspend fun getSimCardById(simcardId: Int): List<SimCard>
    suspend fun getSimCardByNumber(number: String): List<SimCard>
    suspend fun addNewSimCard(simcard: SimCard): Response<SimCard>
    suspend fun deleteSimCardById(simcardId: Int): Response<SimCard>
    suspend fun updateSimCardIsLocked(simcardId: Int, locked: Locked): Response<Locked>
}