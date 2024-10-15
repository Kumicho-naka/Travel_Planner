package com.example.travelplanner.ui

import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.travelplanner.R
import com.example.travelplanner.model.TravelPlan
import com.example.travelplanner.util.DateUtil
import com.example.travelplanner.util.ImageUtil
import com.example.travelplanner.viewModel.PlanViewModel
import com.example.travelplanner.viewModelInterface.FakePlanUpdateDataProvider
import com.example.travelplanner.viewModelInterface.PlanResultData

@Composable
fun PlanResultScreen(navController: NavController, planResultData: PlanResultData) {

    val today = DateUtil.resetTodayWithResetTIme()
    val filteredPlans = planResultData.plans
        .filter { it.date >= today } // 今日以降のみ表示
        .sortedBy { it.date }

    AppScreenWithHeader(
        title = "プラン確認",
        onBackClick = {navController.navigate("main")}
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = ImageUtil.getBackgroundImage(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.5F
            )

            if (filteredPlans.isEmpty()) {
                Text(
                    text = "作成されたプランが\nありません.",
                    modifier = Modifier.align(Alignment.Center)
                        .background(Color.Cyan),
                    style = MaterialTheme.typography.headlineLarge
                )
            } else {
                LazyColumn {
                    items(filteredPlans) { plan ->
                        PlanItem(plan = plan, buttonText = "詳細", onClick = {
                            navController.navigate("webview/${plan.destination}")
                        })
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanResultPreview(){

    PlanResultScreen(navController = NavController(LocalContext.current),
        FakePlanUpdateDataProvider().values.first())
}