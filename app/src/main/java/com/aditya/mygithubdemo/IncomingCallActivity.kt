package com.aditya.mygithubdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class IncomingCallActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IncomingCallScreen(
                callerName = "John Doe",
                onAnswer = {

                    finish()
                },
                onReject = {
                    finish()
                }
            )
        }
    }
}


@Composable
fun IncomingCallScreen(callerName: String, onAnswer: () -> Unit, onReject: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Incoming Call", color = Color.White, fontSize = 24.sp)
        Text(text = callerName, color = Color.White, fontSize = 20.sp)
        Row {
            Button(onClick = onReject) {
                Text("Reject")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onAnswer) {
                Text("Answer")
            }
            Button(onClick = onAnswer) {
                Text("Some thing else")
            }
        }
    }
}

