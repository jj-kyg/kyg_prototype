package com.kyg_prototype.kyg

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import com.kyg_prototype.kyg.ui.theme.KYGTheme


class MainActivity : ComponentActivity() {

    val gameViewModel by viewModels<GameViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KYGTheme {
                MainScreen(gameViewModel)

            }
        }
    }
}
