package com.mzhadan.phoneaccounting.models

data class SystemInfo(
    val model: String,
    val manufacturer: String,
    val osVersion: String,
    val osVersionConverted: String,
    val firmWare: String,
    val supportedArch: String
)
