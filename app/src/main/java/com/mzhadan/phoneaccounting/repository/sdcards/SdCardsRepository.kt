package com.mzhadan.phoneaccounting.repository.sdcards

import com.mzhadan.phoneaccounting.remote.entities.SdCard
import retrofit2.Response
import retrofit2.http.*

interface SdCardsRepository {
    suspend fun getAllSdCards(): List<SdCard>
    suspend fun getSdCardById(sdcardId: Int): List<SdCard>
    suspend fun getSdCardBySerialNumber(serialNumber: String): List<SdCard>
    suspend fun addNewSdCard(sdcard: SdCard): Response<SdCard>
    suspend fun deleteSdCardById(sdcardId: Int): Response<SdCard>
}