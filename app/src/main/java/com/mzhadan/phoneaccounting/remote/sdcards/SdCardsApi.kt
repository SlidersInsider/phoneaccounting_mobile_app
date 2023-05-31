package com.mzhadan.phoneaccounting.remote.sdcards

import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.remote.entities.SdCard
import retrofit2.http.*

interface SdCardsApi {
    @GET("sdcards/all")
    suspend fun getAllSdCards(): List<SdCard>

    @GET("sdcards/id/{sdcardId}")
    suspend fun getSdCardById(
        @Path("sdcardId")
        sdcardId: Int
    ): List<SdCard>

    @GET("sdcards/serialNumber/{serialNumber}")
    suspend fun getSdCardBySerialNumber(
        @Path("serialNumber")
        serialNumber: String
    ): List<SdCard>

    @POST("sdcards/add/sdcard")
    suspend fun addNewSdCard(
        @Body
        sdcard: SdCard
    )

    @DELETE("sdcards/delete/sdcard/{sdcardId}")
    suspend fun deleteSdCardById(
        @Path("sdcardId")
        sdcardId: Int
    )
}