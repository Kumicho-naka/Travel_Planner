package com.example.travelplanner

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.travelplanner.viewModel.PlanViewModelFactory
import com.example.travelplanner.viewModelInterface.PlanCreateData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp()
            }
        }
    }

@Composable
fun MainApp(){
    val context = LocalContext.current

    val prefs = context.getSharedPreferences("travel_plans", Context.MODE_PRIVATE)
    val planViewModel: PlanViewModel = viewModel(
        factory = PlanViewModelFactory(prefs)
    )

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main"){
        composable("main") { MainScreen(navController) } //メイン画面に移行
        composable("plan_creation") { PlanCreateScreen(navController, PlanCreateData{plan -> planViewModel.addPlan(plan)}) } //プラン作成画面に移行
        composable("plan_result") { PlanResultScreen(navController, planViewModel) } //プラン確認画面に移行
        composable("webview/{destination}") { navBackStackEntry ->
            val destination = navBackStackEntry.arguments?.getString("destination") ?: ""
            WebViewScreen(navController, destination, planViewModel)
        } //プランのネットワーク情報画面に移行
        composable("plan_delete") { PlanDeleteScreen(navController = navController, planViewModel = planViewModel) } //プラン削除画面に移行
    }
}