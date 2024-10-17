package com.example.travelplanner.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.travelplanner.data.model.TravelPlan
import com.example.travelplanner.repository.TravelPlanRepository
import com.example.travelplanner.util.CoreUtil.resetTodayWithResetTIme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class PlanViewModel@Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val travelPlanRepository: TravelPlanRepository
): ViewModel() {
    init {
        removeExpiredPlans()
    }

    // プランの追加
    fun addPlan(plan: TravelPlan){
        travelPlanRepository.addPlan(plan)
    }

    // プランの削除
    fun removePlan(plan: TravelPlan){
        travelPlanRepository.deletePlan(plan)
    }

    // プランの更新
    fun updatePlan(updatePlan: TravelPlan){
        travelPlanRepository.updatePlan(updatePlan)
    }

    // 全プランの取得
    fun getAllPlans():List<TravelPlan>{
        return travelPlanRepository.getAllPlans()
    }

    // 期限の切れた(昨日までの)プランを削除する
    private fun removeExpiredPlans(){
        travelPlanRepository.deletePlanOlderYesterday()
    }

}