package com.example.travelplanner.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.travelplanner.viewModelInterface.FakePlanDeleteDataProvider
import com.example.travelplanner.viewModelInterface.PlanDeleteData

@Composable
fun PlanDeleteScreen(navController: NavController, planDeleteData: PlanDeleteData){

    val plans = planDeleteData.plans

    var showDialog by remember { mutableStateOf(false) }
    var planToDelete by remember { mutableStateOf<TravelPlan?>(null) }

    AppScreenWithHeader(
        title = "プラン削除",
        onBackClick = {navController.navigate("main")}
    ) {

        Box(modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
            Image(
                painter = painterResource(R.drawable.colcbord_free),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.5F
            )

            if (plans.isEmpty()) {
                Text(
                    text = "削除できるプランがありません.",
                    modifier = Modifier.align(Alignment.Center)
                        .background(Color.Cyan),
                    style = MaterialTheme.typography.headlineLarge
                )
            } else {
                LazyColumn {
                    items(plans) { plan ->
                        PlanItem(plan = plan, buttonText = "削除", onClick = {
                            planToDelete = plan
                            showDialog = true
                        })
                    }
                }
            }
        }

        CheckDialog(
            showDialog = showDialog,
            title = "プランの削除",
            message = "このプランを削除しますか.",
            confirmLabel = "はい",
            dismissLabel = "いいえ",
            onConfirm = {
                planToDelete?.let {
                    planDeleteData.removePlan(it)
                    showDialog = false
                    planToDelete = null
                }
            },
            onDismiss = { showDialog = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlanDeletePreview(){
    PlanDeleteScreen(navController = NavController(LocalContext.current),FakePlanDeleteDataProvider().values.first())
}