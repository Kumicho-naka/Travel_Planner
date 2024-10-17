package com.example.travelplanner.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

@Entity(tableName = "travelPlan")
data class TravelPlan (
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val date: Date,
    val destination: String,
    var url: String? = null
)