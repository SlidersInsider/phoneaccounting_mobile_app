package com.mzhadan.phoneaccounting.local.phones

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mzhadan.phoneaccounting.local.entities.LocalPhoneInfo

@Dao
interface PhonesDao {
    @Query("select * from ${LocalPhoneInfo.TABLE_NAME}")
    suspend fun getAllPhonesInfo(): List<LocalPhoneInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllPhonesInfo(phoneInfoList: List<LocalPhoneInfo>)
}