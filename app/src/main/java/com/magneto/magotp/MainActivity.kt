package com.magneto.magotp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.magneto.magotp.ui.theme.MagotpTheme
import com.magneto.magotplib.Screen.OtpComposableFilled

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                MagotpTheme() {
                    OtpComposableFilled(
                        heightInDP = 45.dp,
                        widthInDp = 45.dp,
                        cursorColor =  Color.Black,
                        cornerRadius = 8.dp ,
                        passwordToggle = false,
                        arrangement = Arrangement.SpaceEvenly ,
                        modifier = Modifier.fillMaxWidth())
                        {
                            Log.d("LogTag", it.toString())
                        }
                }
            }
        }
    }
}


