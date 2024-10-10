package com.example.travelplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat.Style
import com.example.travelplanner.ui.theme.TravelPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelPlannerTheme {
                MainScreen()
                }
            }
        }
    }

@Composable
fun MainScreen(){
    val image = painterResource(R.drawable.free_item_main_background)
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = image,
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
            Text(
                text = "旅行日程管理",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(64.dp)) {
                Text(text = "プラン作成",
                    style =  MaterialTheme.typography.headlineMedium
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(64.dp)) {
                Text("プラン確認",
                    style =  MaterialTheme.typography.headlineMedium
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(64.dp)) {
                Text("プラン削除",
                    style =  MaterialTheme.typography.headlineMedium
                )
            }

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "旅行日程管理",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {},
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(64.dp)) {
            Text(text = "プラン作成",
                style =  MaterialTheme.typography.headlineMedium
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {},
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(64.dp)) {
            Text("プラン確認",
                style =  MaterialTheme.typography.headlineMedium
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {},
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(64.dp)) {
            Text("プラン削除",
                style =  MaterialTheme.typography.headlineMedium
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun TravelPlannerPreview(){
    TravelPlannerTheme {
        MainScreen()
    }
}