package com.example.travelplanner.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.travelplanner.R

object ImageUtil {
    @Composable
    fun getBackgroundImage(): Painter{
        return painterResource(R.drawable.colcbord_free)
    }
}