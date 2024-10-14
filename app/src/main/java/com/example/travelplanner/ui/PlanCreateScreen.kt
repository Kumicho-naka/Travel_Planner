package com.example.travelplanner.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelplanner.R
import com.example.travelplanner.model.TravelPlan
import com.example.travelplanner.viewModel.PlanViewModel
import com.example.travelplanner.viewModel.PlanViewModelFactory
import com.example.travelplanner.viewModelInterface.FakePlanCreateDataProvider
import com.example.travelplanner.viewModelInterface.PlanCreateData
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun PlanCreateScreen(navController: NavController,planCreateData: PlanCreateData){
    val context = LocalContext.current

    val calendar = Calendar.getInstance()

    var selectedDate by remember { mutableStateOf(calendar.time) }
    var destination by remember { mutableStateOf("") }

    var showDatePickDialog by remember { mutableStateOf(false) }
    var showSaveDialog by remember { mutableStateOf(false) }
    var showCancelDialog by remember { mutableStateOf(false) }

    val image = painterResource(R.drawable.colcbord_free)

    AppScreenWithHeader(
        title = "プラン作成",
        onBackClick = {navController.navigate("main")}
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.5F
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate),
                    onValueChange = {},
                    label = { Text("日付") },
                    readOnly = true,
                    modifier = Modifier
                        .clickable { showDatePickDialog = true },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = destination,
                    onValueChange = { destination = it },
                    label = { Text("旅行先") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val newPlan = TravelPlan(
                            date = selectedDate,
                            destination = destination
                        )
                        planCreateData.addPlan(newPlan)
                        showSaveDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(64.dp)
                )
                {
                    Text(
                        "プラン保存",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (selectedDate != Calendar.getInstance().time ||
                            destination.isNotEmpty()
                        ) {
                            showCancelDialog = true
                        } else {
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(64.dp)
                ) {
                    Text(
                        "キャンセル",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
    if(showDatePickDialog){
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(year, month, dayOfMonth)
                selectedDate = calendar.time
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
    if (showSaveDialog){
        // 保存後の選択肢ダイアログ
        CheckDialog(
            showDialog = showSaveDialog,
            title = "次のアクションを選択してください",
            message = "プランを確認するか, 続けてプランを作成しますか?",
            confirmLabel = "プランを確認",
            dismissLabel = "続けてプランを作成",
            onConfirm = {
                showSaveDialog = false
                navController.navigate("plan_result")
            },
            onDismiss = {
                showSaveDialog = false
                destination = ""
            }
        )
    }
    if (showCancelDialog){
        // キャンセル時のダイアログ
        CheckDialog(
            showDialog = showCancelDialog,
            title = "プランの破棄",
            message = "入力中のプランを破棄しますか?",
            confirmLabel = "はい",
            dismissLabel = "いいえ",
            onConfirm = {
                showCancelDialog = false
                navController.popBackStack()
            },
            onDismiss = { showCancelDialog = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlanCreatePreview(){
    //TODO:NavControllerで渡すのは美しくないので、PlanCreateDataのような方法で渡す。
    // (PlanCreateDataに内包してもいいかも)
    PlanCreateScreen(navController = NavController(LocalContext.current), FakePlanCreateDataProvider().values.first())
}