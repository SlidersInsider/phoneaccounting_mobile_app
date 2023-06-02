package com.mzhadan.phoneaccounting.repository.sdcards

import com.mzhadan.phoneaccounting.remote.entities.SdCard
import com.mzhadan.phoneaccounting.remote.notifications.NotificationsApi
import com.mzhadan.phoneaccounting.remote.sdcards.SdCardsApi
import com.mzhadan.phoneaccounting.repository.notifications.NotificationsRepository
import retrofit2.Response
import javax.inject.Inject

class SdCardsRepositoryImpl @Inject constructor(
    private val sdCardsApi: SdCardsApi
): SdCardsRepository {
    override suspend fun getAllSdCards(): List<SdCard> =
        sdCardsApi.getAllSdCards()

    override suspend fun getSdCardById(sdcardId: Int): List<SdCard> =
        sdCardsApi.getSdCardById(sdcardId)

    override suspend fun getSdCardBySerialNumber(serialNumber: String): List<SdCard> =
        sdCardsApi.getSdCardBySerialNumber(serialNumber)

    override suspend fun addNewSdCard(sdcard: SdCard): Response<SdCard> =
        sdCardsApi.addNewSdCard(sdcard)

    override suspend fun deleteSdCardById(sdcardId: Int): Response<SdCard> =
        sdCardsApi.deleteSdCardById(sdcardId)

}