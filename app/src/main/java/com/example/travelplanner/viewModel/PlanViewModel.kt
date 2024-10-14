package com.example.travelplanner.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.travelplanner.model.TravelPlan
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlanViewModel(private val prefs: SharedPreferences) : ViewModel() {

    private val _plans = mutableListOf<TravelPlan>()
    val plans: List<TravelPlan> get() = _plans

    init {
        loadPlansFromStorage()
    }

    // プランの追加
    fun addPlan(plan: TravelPlan){
        _plans.add(plan)
        savePlansToStorage()
    }

    // プランの削除
    fun removePlan(plan: TravelPlan){
        _plans.remove(plan)
        savePlansToStorage()
    }

    // プランの更新
    fun updatePlan(updatePlan: TravelPlan){
        val index = _plans.indexOfFirst { it.id == updatePlan.id }
        if(index != -1){
            _plans[index] = updatePlan
            savePlansToStorage()
        }
    }

    // SharedPreferencesに保存する
    private fun savePlansToStorage(){
        val editor = prefs.edit()
        val gson = Gson()
        val plansJson = gson.toJson(_plans)
        editor.putString("plans", plansJson)
        editor.apply()
    }

    // SharedPreferencesからロードする
    private fun loadPlansFromStorage(){
        val plansJson = prefs.getString("plans",null)
        if(plansJson != null){
            val gson = Gson()
            val type = object  : TypeToken<List<TravelPlan>>() {}.type
            val planList: List<TravelPlan> = gson.fromJson(plansJson, type)
            _plans.clear()
            _plans.addAll(planList)
        }
    }

}