package com.example.travelplanner.repository

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.example.travelplanner.data.dao.TravelPlanDao
import com.example.travelplanner.data.model.TravelPlan
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TravelPlanRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val appContext: Context,
    private val travelPlanDao: TravelPlanDao
) : TravelPlanRepository {
    override fun addPlan(plan: TravelPlan) {
        travelPlanDao.insert(plan)
    }

    override fun deletePlan(plan: TravelPlan) {
        travelPlanDao.delete(plan)
    }

    override fun updatePlan(plan: TravelPlan) {
        travelPlanDao.update(plan)
    }

    override fun getAllPlans(): List<TravelPlan> {
        return travelPlanDao.getAllPlans()
    }

    override fun deletePlanOlderYesterday() {
        // 指定の日付を取得 (例: 1日前の日付を取得)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val date = calendar.time

        // DateをLongに変換してクエリに渡す
        travelPlanDao.deletePlanOlderThan(date.time)
    }

}