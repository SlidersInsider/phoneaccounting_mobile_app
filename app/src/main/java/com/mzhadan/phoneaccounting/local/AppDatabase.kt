package com.mzhadan.phoneaccounting.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mzhadan.phoneaccounting.local.entities.LocalPhoneInfo
import com.mzhadan.phoneaccounting.local.phones.PhonesDao

@Database(entities = [LocalPhoneInfo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun phonesDao(): PhonesDao
}