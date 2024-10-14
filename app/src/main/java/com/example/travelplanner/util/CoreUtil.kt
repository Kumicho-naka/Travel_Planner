package com.example.travelplanner.util

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date

object CoreUtil {
    @RequiresApi(Build.VERSION_CODES.O)
    fun Context.ldtToDate(localDateTime: LocalDateTime?): Date {
        val zone = ZoneId.systemDefault()
        val zonedDateTime = ZonedDateTime.of(localDateTime, zone)
        val instant = zonedDateTime.toInstant()
        return Date.from(instant)
    }
}