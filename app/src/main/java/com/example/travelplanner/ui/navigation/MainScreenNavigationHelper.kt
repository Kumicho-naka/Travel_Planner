package com.example.travelplanner.ui.navigation

import androidx.navigation.NavController
import com.example.travelplanner.viewModelInterface.MainScreenNavigation

class MainScreenNavigationHelper(private val navController: NavController) : MainScreenNavigation {
    override fun navigateToPlanCreate() {
        navController.navigate("plan_creation")
    }

    override fun navigateToPlanResult() {
        navController.navigate("plan_result")
    }

    override fun navigateToPlanDelete() {
        navController.navigate("plan_delete")
    }
}