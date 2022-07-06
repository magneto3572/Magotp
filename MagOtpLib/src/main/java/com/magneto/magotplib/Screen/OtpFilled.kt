package com.magneto.magotplib.Screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bis.magotp.ui.screen.SmsRetrieverUserConsentBroadcast
import com.magneto.magotplib.R
import java.time.format.TextStyle

private var string = ""

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpComposableFilled(
    heightInDP: Dp,
    widthInDp : Dp,
    fontSize : Int? = null,
    cursorColor: Color? = null,
    cornerRadius: Dp,
    passwordToggle: Boolean,
    backgroundDrawable: Int? = R.drawable.ic_rectangle_background,
    arrangement: Arrangement.HorizontalOrVertical,
    modifier: Modifier,
    automaticCapture : Boolean? = false,
    otpComposableType : Int = ComposableType.TYPE_FOUR,
    onvaluechange: (String) -> Unit
){

    var otp1 by remember { mutableStateOf("") }
    var otp2 by remember { mutableStateOf("") }
    var otp3 by remember { mutableStateOf("") }
    var otp4 by remember { mutableStateOf("") }
    var otp5 by remember { mutableStateOf("") }
    var otp6 by remember { mutableStateOf("") }


    if (automaticCapture == true){
        SmsRetrieverUserConsentBroadcast(smsCodeLength = otpComposableType){ message, code ->
            otp1 = code[0].toString()
            otp2 = code[1].toString()
            otp3 = code[2].toString()
            otp4 = code[3].toString()

            if (otpComposableType != 4){
                try {
                    otp5 = code[4].toString()
                    otp6 = code[5].toString()
                    string = otp1+otp2+otp3+otp4+otp5+otp6
                    onvaluechange(string)
                }catch (e : Exception){
                    e.toString()
                }
            }else{
                string = otp1+otp2+otp3+otp4
                onvaluechange(string)
            }
        }
    }

    val (item1, item2, item3, item4, item5, item6) = remember { FocusRequester.createRefs() }
    val focusManager = LocalFocusManager.current

    Row(modifier = modifier,
        horizontalArrangement = arrangement) {

        Box(modifier = Modifier
            .size(widthInDp, heightInDP)
        ) {

            if(backgroundDrawable != null){
                Image(modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(cornerRadius)), painter = painterResource(id = backgroundDrawable), contentDescription ="null")
            }

            BasicTextField(
                modifier = Modifier
                    .align(Alignment.Center)
                    .focusRequester(item1)
                    .onKeyEvent {
                        if (it.key.nativeKeyCode == 67) {
                            otp1 = ""
                            item1.freeFocus()
                            true
                        } else {
                            // let other handlers receive this event
                            false
                        }
                    },
                value = otp1,
                maxLines = 1,
                singleLine = true,
                visualTransformation =  if(passwordToggle) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword,  imeAction = ImeAction.Next),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = fontSize?.sp ?: 14.sp),
                cursorBrush = if (cursorColor != null) SolidColor(cursorColor) else SolidColor(Color.Black),
                onValueChange = {
                    otp1 = it
                    if(it.length == 1){
                        item2.requestFocus()
                    }
                })
        }

        Box(modifier = Modifier
            .size(widthInDp, heightInDP)
        ) {
            if(backgroundDrawable != null){
                Image(modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(cornerRadius)), painter = painterResource(id = backgroundDrawable), contentDescription ="null")
            }

            BasicTextField(
                modifier = Modifier
                    .align(Alignment.Center)
                    .focusRequester(item2)
                    .onKeyEvent {
                        if (it.key.nativeKeyCode == 67) {
                            otp2 = ""
                            item1.requestFocus()
                            item2.freeFocus()
                            true
                        } else {
                            // let other handlers receive this event
                            false
                        }
                    },
                value = otp2,
                maxLines = 1,
                singleLine = true,
                visualTransformation =  if(passwordToggle) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Next),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = fontSize?.sp ?: 14.sp),
                cursorBrush = if (cursorColor != null) SolidColor(cursorColor) else SolidColor(Color.Black),
                onValueChange = {
                    otp2 = it
                    if(it.length == 1){
                        item3.requestFocus()
                    } },
            )
        }

        Box(modifier = Modifier
            .size(widthInDp, heightInDP)
        ) {
            if(backgroundDrawable != null){
                Image(modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(cornerRadius)), painter = painterResource(id = backgroundDrawable), contentDescription ="null")
            }
            BasicTextField(
                modifier = Modifier
                    .align(Alignment.Center)
                    .focusRequester(item3)
                    .onKeyEvent {
                        if (it.key.nativeKeyCode == 67) {
                            otp3 = ""
                            item2.requestFocus()
                            item3.freeFocus()
                            true
                        } else {
                            // let other handlers receive this event
                            false
                        }
                    },
                value = otp3,
                maxLines = 1,
                singleLine = true,
                visualTransformation =  if(passwordToggle) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Next),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = fontSize?.sp ?: 14.sp),
                cursorBrush = if (cursorColor != null) SolidColor(cursorColor) else SolidColor(Color.Black),
                onValueChange = { otp3 = it
                    if(it.length == 1){
                        item4.requestFocus()
                    } },
            )
        }

        Box(modifier = Modifier
            .size(widthInDp, heightInDP)
        ){
            if(backgroundDrawable != null){
                Image(modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(cornerRadius)), painter = painterResource(id = backgroundDrawable), contentDescription ="null")
            }
            BasicTextField(
                modifier = Modifier
                    .align(Alignment.Center)
                    .focusRequester(item4)
                    .onKeyEvent {
                        Log.d("LogTag", it.key.nativeKeyCode.toString())
                        if (it.key.nativeKeyCode == 67) {
                            string = ""
                            otp4 = ""
                            item3.requestFocus()
                            item4.freeFocus()
                            true
                        } else {
                            // let other handlers receive this event
                            false
                        }
                    },
                value = otp4,
                maxLines = 1,
                singleLine = true,
                visualTransformation =  if(passwordToggle) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = fontSize?.sp ?: 14.sp),
                cursorBrush = if (cursorColor != null) SolidColor(cursorColor) else SolidColor(Color.Black),
                onValueChange = {
                    if(it.length == 1){
                        otp4 = it
                        if(otpComposableType != 4){
                            item5.requestFocus()
                        }else{
                            string = otp1+otp2+otp3+otp4
                            onvaluechange(string)
                            focusManager.clearFocus()
                        }
                    }else{
                        otp4 = ""
                    } },
            )
        }

        if(otpComposableType != 4){
            Box(modifier = Modifier
                .size(widthInDp, heightInDP)
            ){
                if(backgroundDrawable != null){
                    Image(modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(cornerRadius)), painter = painterResource(id = backgroundDrawable), contentDescription ="null")
                }
                BasicTextField(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .focusRequester(item5)
                        .onKeyEvent {
                            Log.d("LogTag", it.key.nativeKeyCode.toString())
                            if (it.key.nativeKeyCode == 67) {
                                string = ""
                                otp5 = ""
                                item4.requestFocus()
                                item5.freeFocus()
                                true
                            } else {
                                // let other handlers receive this event
                                false
                            }
                        },
                    value = otp5,
                    maxLines = 1,
                    singleLine = true,
                    visualTransformation =  if(passwordToggle) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = fontSize?.sp ?: 14.sp),
                    cursorBrush = if (cursorColor != null) SolidColor(cursorColor) else SolidColor(Color.Black),
                    onValueChange = {
                        otp5 = it
                        if(it.length == 1){
                            if(otpComposableType != 4){
                                item6.requestFocus()
                            }
                        }else{
                            otp5 = ""
                        } },
                )
            }

            Box(modifier = Modifier
                .size(widthInDp, heightInDP)
            ){
                if(backgroundDrawable != null){
                    Image(modifier = Modifier
                        .background(Color.Green)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(cornerRadius)),
                        painter = painterResource(id = backgroundDrawable),
                        contentDescription ="null")
                }
                BasicTextField(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .focusRequester(item6)
                        .onKeyEvent {
                            Log.d("LogTag", it.key.nativeKeyCode.toString())
                            if (it.key.nativeKeyCode == 67) {
                                string = ""
                                otp6 = ""
                                item5.requestFocus()
                                item6.freeFocus()
                                true
                            } else {
                                // let other handlers receive this event
                                false
                            }
                        },
                    value = otp6,
                    maxLines = 1,
                    singleLine = true,
                    visualTransformation =  if(passwordToggle) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = fontSize?.sp ?: 14.sp),
                    cursorBrush = if (cursorColor != null) SolidColor(cursorColor) else SolidColor(Color.Black),
                    onValueChange = {
                        if(it.length == 1){
                            if(otpComposableType != 4){
                                otp6 = it
                                string = otp1+otp2+otp3+otp4+otp5+otp6
                                onvaluechange(string)
                                focusManager.clearFocus()
                            }
                        }else{
                            otp6 = ""
                        } },
                )
            }
        }
    }
}

