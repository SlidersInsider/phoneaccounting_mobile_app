package com.mzhadan.phoneaccounting.local.phones

import androidx.room.Dao
import androidx.room.Query
import com.mzhadan.phoneaccounting.local.entities.LocalPhoneInfo
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo

@Dao
interface PhonesDao {
    @Query("select * from ${LocalPhoneInfo.TABLE_NAME}")
    suspend fun getAllPhonesInfo(): List<LocalPhoneInfo>
}