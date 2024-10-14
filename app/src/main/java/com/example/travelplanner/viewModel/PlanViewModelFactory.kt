package com.example.travelplanner.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlanViewModelFactory(private val prefs: SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PlanViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PlanViewModel(prefs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}