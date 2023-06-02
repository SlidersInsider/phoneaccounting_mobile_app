package com.mzhadan.phoneaccounting.remote.phones

import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.remote.entities.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PhonesApi {
    @GET("phones/")
    suspend fun getAllPhonesInfo(): List<PhoneInfo>

    @GET("phones/{phoneId}/")
    suspend fun getPhoneById(
        @Path("phoneId")
        phoneId: Int
    ): PhoneInfo

    @GET("phones/manufacturer/{mnfctr}")
    suspend fun getPhoneByManufacturer(
        @Path("mnfctr/")
        mnfctr: String
    ): PhoneInfo

    @POST("phones/")
    suspend fun addNewPhoneInfo(
        @Body
        phoneInfo: PhoneInfo
    ): Response<PhoneInfo>

    @DELETE("phones/{phoneId}/")
    suspend fun deletePhoneInfoById(
        @Path("phoneId")
        phoneId: Int
    ): Response<PhoneInfo>

    @PATCH("phones/{phoneId}/")
    suspend fun updatePhoneInfoUser(
        @Path("phoneId")
        phoneId: Int,
        @Body
        user: User
    ): Response<User>
}