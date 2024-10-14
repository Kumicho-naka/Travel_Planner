package com.example.travelplanner.ui

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelplanner.model.TravelPlan
import java.util.Locale

@Composable
fun PlanItem(plan: TravelPlan,
             buttonText: String,
             onClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column {
            Text(text = "${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(plan.date)}")
            Text(text = "旅行先: ${plan.destination}")
        }
        Button(onClick = onClick) {
            Text(buttonText)
        }
    }
}