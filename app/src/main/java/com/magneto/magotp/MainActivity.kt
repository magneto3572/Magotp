package com.magneto.magotp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.magneto.magotplib.Screen.OtpComposableOutlined

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                MagotpTheme() {
                    Column {
                        OtpComposableOutlined(
                            heightInDp = 55.dp,
                            widthInDp = 55.dp,
                            cornerRadius = 8.dp ,
                            focusColor =  Color.Black,
                            unfocusColor = Color.LightGray,
                            passwordToggle = false,
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = Color.White,
                            onvaluechange = {
                                Log.d("LogTagOnValueChnage", it)
                            })

                        OtpComposableFilled(
                            heightInDP = 55.dp,
                            widthInDp = 55.dp,
                            cornerRadius = 8.dp ,
                            cursorColor = Color.Black,
                            passwordToggle = false,
                            arrangement = Arrangement.SpaceEvenly ,
                            modifier = Modifier.fillMaxWidth(),
                            onvaluechange = {
                                Log.d("LogTagOnValueChnage", it)
                            })
                    }

                }
            }
        }
    }
}


