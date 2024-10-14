package com.example.travelplanner.model

import java.util.Date
import java.util.UUID

data class TravelPlan (
    val id: String = UUID.randomUUID().toString(),
    val date: Date,
    val destination: String,
    var url: String? = null
)