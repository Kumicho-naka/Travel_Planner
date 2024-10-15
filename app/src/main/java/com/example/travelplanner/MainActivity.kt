package com.example.travelplanner

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelplanner.model.TravelPlan
import com.example.travelplanner.ui.MainScreen
import com.example.travelplanner.ui.PlanCreateScreen
import com.example.travelplanner.ui.PlanDeleteScreen
import com.example.travelplanner.ui.PlanResultScreen
import com.example.travelplanner.ui.WebViewScreen
import com.example.travelplanner.viewModel.PlanViewModel
import com.example.travelplanner.viewModelInterface.PlanCreateData
import com.example.travelplanner.viewModelInterface.PlanDeleteData
import com.example.travelplanner.viewModelInterface.PlanResultData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val planViewModel: PlanViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp(planViewModel)
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainApp(planViewModel: PlanViewModel){
    val context = LocalContext.current

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main"){
        composable("main") { MainScreen(navController) } //メイン画面に移行
        composable("plan_creation") {
            PlanCreateScreen(
                navController,
                PlanCreateData{
                    plan -> planViewModel.addPlan(plan)
                }
            )
        } //プラン作成画面に移行
        composable("plan_result") {
            PlanResultScreen(
                navController,
                planResultData = PlanResultData(
                    updatePlan = { plan -> planViewModel.updatePlan(plan)},
                    plans = planViewModel.plans
                )
            )
        } //プラン確認画面に移行
        composable("webview/{destination}") { navBackStackEntry ->
            val destination = navBackStackEntry.arguments?.getString("destination") ?: ""
            WebViewScreen(navController, destination, planViewModel)
        } //プランのネットワーク情報画面に移行
        composable("plan_delete") {
            PlanDeleteScreen(
                navController = navController,
                planDeleteData = PlanDeleteData(
                    removePlan = { plan -> planViewModel.removePlan(plan)},
                    plans = planViewModel.plans
                )
            )
        } //プラン削除画面に移行
    }
}