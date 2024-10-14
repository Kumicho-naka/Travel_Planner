package com.example.travelplanner.ui

import android.webkit.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelplanner.viewModel.PlanViewModel

@Composable
fun WebViewScreen(
    navController: NavController,
    destination: String,
    planViewModel: PlanViewModel = viewModel()
){
    val travelPlan = planViewModel.plans.find { it.destination == destination }

    val initialUrl = travelPlan?.url?.takeIf { it.isNotEmpty() }
        ?: "https://www.google.com/search?q=${destination}"

    var url by remember { mutableStateOf(initialUrl) }
    var showDialog by remember { mutableStateOf(false) }
    var webView: WebView? by remember { mutableStateOf(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        // URL入力欄
        OutlinedTextField(
            value = url,
            onValueChange = { newUrl -> url = newUrl},
            label = { Text("URLを入力") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    webView?.loadUrl(url)
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )

        // WebViewの表示
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webView = this
                    loadUrl(url)
                }
            },
            update = {
                webView ->
                webView.loadUrl(url)
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )

        // WebViewのボタン
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = { webView?.goBack() }, enabled = webView?.canGoBack() == true) {
                Text("戻る")
            }

            Button(onClick = { webView?.goForward() }, enabled = webView?.canGoForward() == true) {
                Text("進む")
            }

            Button(onClick = { showDialog = true }) {
                Text("URLを更新")
            }
        }

        CheckDialog(
            showDialog = showDialog,
            title = "URL行進",
            message = "詳細からここにアクセスするようにしますか？",
            confirmLabel = "はい",
            dismissLabel = "いいえ",
            onConfirm = {
                // URLを対象のTravelPlanのurlに保存
                val currentUrl = webView?.url ?: ""
                if(currentUrl.isNotEmpty()){
                    travelPlan?.let {
                        it.url = currentUrl
                        planViewModel.updatePlan(it)
                    }
                }
                showDialog = false

            },
            onDismiss = {
                showDialog = false
            }
        )

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround){
            Button(onClick = {navController.popBackStack()}) {
                Text("確認へ戻る")
            }
            Button(onClick = { navController.navigate("main") }) {
                Text("メインに戻る")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WebViewPreview(){
    WebViewScreen(navController = NavController(LocalContext.current), destination = "test")
}