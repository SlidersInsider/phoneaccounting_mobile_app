package com.mzhadan.phoneaccounting.remote.phones

import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PhonesApi {
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

    @POST("phones/add/phone")
    suspend fun addNewPhoneInfo(
        @Body
        phoneInfo: PhoneInfo
    )

    @DELETE("phones/delete/phone/{phoneId}")
    suspend fun deletePhoneInfoById(
        @Path("phoneId")
        phoneId: Int
    )

    @PUT("phones/update/user/{phoneId}")
    suspend fun updatePhoneInfoUser(
        @Path("phoneId")
        phoneId: Int,
        @Body
        name: String
    )
}