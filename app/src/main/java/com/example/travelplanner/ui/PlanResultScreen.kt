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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.travelplanner.R
import com.example.travelplanner.model.TravelPlan
import com.example.travelplanner.viewModel.PlanViewModel
import com.example.travelplanner.viewModel.PlanViewModelFactory

@Composable
fun PlanResultScreen(navController: NavController, planViewModel: PlanViewModel) {
    AppScreenWithHeader(
        title = "プラン確認",
        onBackClick = {navController.navigate("main")}
    ) {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

        val filteredPlans = planViewModel.plans
            .filter { it.date >= today } // 今日以降のみ表示
            .sortedBy { it.date }

        val image = painterResource(R.drawable.colcbord_free)
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = image,
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
    // モックのSharedPreferencesを作成
    val context = LocalContext.current
    val mockPrefs: SharedPreferences = context.getSharedPreferences("mock_prefs", Context.MODE_PRIVATE)

    // PlanViewModelFactoryを使ってPlanViewModelを作成
    val mockPlanViewModel = PlanViewModelFactory(mockPrefs).create(PlanViewModel::class.java)

    // 日付をDate型で生成
    val mockDate = Calendar.getInstance().apply {
        set(2024, 10, 14)
    }.time
    mockPlanViewModel.addPlan(TravelPlan(date = mockDate, destination = "Test Destination"))
    PlanResultScreen(navController = NavController(LocalContext.current),mockPlanViewModel)
}