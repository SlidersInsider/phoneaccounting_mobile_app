package com.mzhadan.phoneaccounting.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalPhoneInfo.TABLE_NAME)
data class LocalPhoneInfo(
    @PrimaryKey
    val phoneId: Int,
    @ColumnInfo(name = "model")
    val model: String,
    @ColumnInfo(name = "manufacturer")
    val manufacturer: String,
    @ColumnInfo(name = "osVersion")
    val osVersion: String,
    @ColumnInfo(name = "firmware")
    val firmware: String,
    @ColumnInfo(name = "supportedArch")
    val supportedArch: String,
    @ColumnInfo(name = "user")
    val user: String,
    @ColumnInfo(name = "simSlotsCount")
    val simSlotsCount: Int,
    @ColumnInfo(name = "simcard1")
    var simcard1: String,
    @ColumnInfo(name = "simcard2")
    var simcard2: String,
    @ColumnInfo(name = "sdSlotsCount")
    val sdSlotsCount: Int,
    @ColumnInfo(name = "sdcard")
    val sdcard: String
) {
    companion object {
        const val TABLE_NAME: String = "phones_info"
    }
}