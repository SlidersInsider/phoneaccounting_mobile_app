package com.mzhadan.phoneaccounting.api

import com.mzhadan.phoneaccounting.models.entities.PhoneInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PhoneAccountingApi {
    @GET("phones/all")
    suspend fun getAllPhonesInfo(): List<PhoneInfo>

    @GET("phones/id/{phoneId}")
    suspend fun getPhoneById(
        @Path("phoneId")
        phoneId: Int
    ): List<PhoneInfo>

    @GET("phones/manufacturer/{mnfctr}")
    suspend fun getPhoneByManufacturer(
        @Path("mnfctr")
        mnfctr: String
    ): List<PhoneInfo>

    @POST("phones/add")
    suspend fun addNewPhoneInfo(
        @Body
        phoneInfo: PhoneInfo
    )
}