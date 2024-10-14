package com.example.travelplanner.util

import java.util.Calendar
import java.util.Date

object DateUtil {
    // 今日の日付を時刻等を0にしたDateを返す。
    fun resetTodayWithResetTIme(): Date{
       return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time
    }
}