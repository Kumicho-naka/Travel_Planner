package com.example.travelplanner.viewModelInterface

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.travelplanner.model.TravelPlan

data class PlanDeleteData (
    val removePlan:(TravelPlan) -> Unit,
    val plans:List<TravelPlan>
)


/**
 * プレビュー用
 */
class FakePlanDeleteDataProvider : PreviewParameterProvider<PlanDeleteData> {
    override val values: Sequence<PlanDeleteData> = sequenceOf(
        PlanDeleteData(
            plans = listOf(),
            removePlan = {}
        )
    )
}