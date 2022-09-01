package com.olehvynnytskyi.android.flashlight

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.olehvynnytskyi.android.flashlight.ui.theme.FlashLightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            FlashLightTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TurnOnFlashlight()
                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun TurnOnFlashlight() {
    var isClicked by remember {
        mutableStateOf(false)
    }
    val cameraM = LocalContext.current.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    val cameraListId = cameraM.cameraIdList[0]
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                isClicked = if (isClicked.not()) {
                    cameraM.setTorchMode(cameraListId, true)
                    true
                } else {
                    cameraM.setTorchMode(cameraListId, false)
                    false
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_flashlight),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = if (isClicked) Color.Yellow else Color.Red
            )
        )
    }
}