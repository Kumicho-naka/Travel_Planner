package com.example.travelplanner.viewModelInterface

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.travelplanner.model.TravelPlan

data class PlanResultData (
    val updatePlan:(TravelPlan) -> Unit,
    val plans:List<TravelPlan>
    )


/**
 * プレビュー用
 */
class FakePlanUpdateDataProvider : PreviewParameterProvider<PlanResultData> {
    override val values: Sequence<PlanResultData> = sequenceOf(
        PlanResultData(
            plans = listOf(),
            updatePlan = {}
        )
    )
}