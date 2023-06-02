package com.mzhadan.phoneaccounting.remote.simcards

import com.mzhadan.phoneaccounting.remote.entities.Locked
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.remote.entities.SdCard
import com.mzhadan.phoneaccounting.remote.entities.SimCard
import retrofit2.Response
import retrofit2.http.*

interface SimCardsApi {
    @GET("simcards/")
    suspend fun getAllSimCards(): List<SimCard>

    @GET("simcards/{simcardId}/")
    suspend fun getSimCardById(
        @Path("simcardId")
        simcardId: Int
    ): List<SimCard>

    @GET("simcards/number/{number}")
    suspend fun getSimCardByNumber(
        @Path("number")
        number: String
    ): List<SimCard>

    @POST("simcards/")
    suspend fun addNewSimCard(
        @Body
        simcard: SimCard
    ): Response<SimCard>

    @DELETE("simcards/{simcardId}/")
    suspend fun deleteSimCardById(
        @Path("simcardId")
        simcardId: Int
    ): Response<SimCard>

    @PATCH("simcards/{simcardId}/")
    suspend fun updateSimCardIsLocked(
        @Path("simcardId")
        simcardId: Int,
        @Body
        locked: Locked
    ): Response<Locked>
}