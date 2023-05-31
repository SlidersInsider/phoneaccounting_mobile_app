package com.mzhadan.phoneaccounting.remote.simcards

import com.mzhadan.phoneaccounting.remote.entities.SdCard
import com.mzhadan.phoneaccounting.remote.entities.SimCard
import retrofit2.http.*

interface SimCardsApi {
    @GET("simcards/all")
    suspend fun getAllSimCards(): List<SimCard>

    @GET("simcards/id/{simcardId}")
    suspend fun getSimCardById(
        @Path("simcardId")
        simcardId: Int
    ): List<SimCard>

    @GET("simcards/number/{number}")
    suspend fun getSimCardByNumber(
        @Path("number")
        number: String
    ): List<SimCard>

    @POST("simcards/add/simcard")
    suspend fun addNewSimCard(
        @Body
        simcard: SimCard
    )

    @DELETE("simcards/delete/simcard/{simcardId}")
    suspend fun deleteSimCardById(
        @Path("simcardId")
        simcardId: Int
    )

    @PUT("simcards/update/simcard/{simcardId}")
    suspend fun updateSimCardIsLocked(
        @Path("simcardId")
        simcardId: Int,
        @Body
        isLocked: String
    )
}