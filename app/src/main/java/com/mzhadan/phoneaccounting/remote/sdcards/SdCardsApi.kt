package com.mzhadan.phoneaccounting.remote.sdcards

import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.remote.entities.SdCard
import retrofit2.Response
import retrofit2.http.*

interface SdCardsApi {
    @GET("sdcards/")
    suspend fun getAllSdCards(): List<SdCard>

    @GET("sdcards/{sdcardId}/")
    suspend fun getSdCardById(
        @Path("sdcardId")
        sdcardId: Int
    ): List<SdCard>

    @GET("sdcards/serialNumber/{serialNumber}")
    suspend fun getSdCardBySerialNumber(
        @Path("serialNumber")
        serialNumber: String
    ): List<SdCard>

    @POST("sdcards/")
    suspend fun addNewSdCard(
        @Body
        sdcard: SdCard
    ): Response<SdCard>

    @DELETE("sdcards/{sdcardId}/")
    suspend fun deleteSdCardById(
        @Path("sdcardId")
        sdcardId: Int
    ): Response<SdCard>
}