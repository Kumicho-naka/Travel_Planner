package com.example.travelplanner.viewModelInterface

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.travelplanner.model.TravelPlan

data class WebViewData(
    val updatePlan:(TravelPlan) -> Unit,
    val plans:List<TravelPlan>,
    val navigateToMain: () -> Unit,
    val navigateToResult: () -> Unit
)

/**
 * プレビュー用
 */
class FakeWebViewDataProvider : PreviewParameterProvider<WebViewData> {
    override val values: Sequence<WebViewData> = sequenceOf(
        WebViewData(
            plans = listOf(),
            updatePlan = {},
            navigateToMain = {},
            navigateToResult = {}
        )
    )
}