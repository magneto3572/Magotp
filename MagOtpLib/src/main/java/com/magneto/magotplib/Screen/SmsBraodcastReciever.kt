package com.bis.magotp.ui.screen

import android.app.Activity
import android.content.*
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

@Composable
internal fun SmsRetrieverUserConsentBroadcast(
    smsCodeLength: Int,
    onSmsReceived: (message: String, code: String) -> Unit,
) {
    val context = LocalContext.current

    var shouldRegisterReceiver by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        Log.d("LogTag", "Initializing Sms Retriever client")
        com.google.android.gms.auth.api.phone.SmsRetriever.getClient(context)
            .startSmsUserConsent(null)
            .addOnSuccessListener {
                Log.d("LogTag", "SmsRetriever started successfully")
                shouldRegisterReceiver = true
            }
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK && it.data != null) {
            val message: String? = it.data!!.getStringExtra(com.google.android.gms.auth.api.phone.SmsRetriever.EXTRA_SMS_MESSAGE)
            message?.let {
                Log.d("LogTag","Sms received: $message")
                val verificationCode = getVerificationCodeFromSms(message, smsCodeLength)
                Log.d("LogTag","Verification code parsed: $verificationCode")

                onSmsReceived(message, verificationCode)
            }
            shouldRegisterReceiver = false
        } else {
            Log.d("LogTag","Consent denied. User can type OTP manually.")
        }
    }

    if (shouldRegisterReceiver) {
        SystemBroadcastReceiver(systemAction = com.google.android.gms.auth.api.phone.SmsRetriever.SMS_RETRIEVED_ACTION,
        ) { intent ->
            if (intent != null && com.google.android.gms.auth.api.phone.SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
                val extras = intent.extras

                val smsRetrieverStatus = extras?.get(com.google.android.gms.auth.api.phone.SmsRetriever.EXTRA_STATUS) as Status
                when (smsRetrieverStatus.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        val consentIntent =
                            extras.getParcelable<Intent>(com.google.android.gms.auth.api.phone.SmsRetriever.EXTRA_CONSENT_INTENT)
                        try {
                            launcher.launch(consentIntent)
                        } catch (e: ActivityNotFoundException) {
                            Log.e("LogTag", "Activity Not found for SMS consent API")
                        }
                    }

                    CommonStatusCodes.TIMEOUT -> Log.d(
                        "LogTag",
                        "Timeout in sms verification receiver"
                    )
                }
            }
        }
    }
}

@Composable
internal fun SystemBroadcastReceiver(
    systemAction: String,
    onSystemEvent: (intent: Intent?) -> Unit
) {
    val context = LocalContext.current
    val currentOnSystemEvent by rememberUpdatedState(onSystemEvent)
    DisposableEffect(context, systemAction) {
        val intentFilter = IntentFilter(systemAction)
        val broadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                currentOnSystemEvent(intent)
            }
        }
        context.registerReceiver(broadcast, intentFilter)

        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }
}

internal fun getVerificationCodeFromSms(sms: String, smsCodeLength: Int): String =
    sms.filter {
        it.isDigit() }
        .substring(0 until smsCodeLength)
