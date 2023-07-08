package com.example.nothingc

import android.content.Context.WINDOW_SERVICE
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService


private fun Add(){}
private fun Subtract(){}
private fun Multiply(){}
private fun Divide(){}
private fun Power(){}
private fun Percentage(){}
private fun Root(){}
private fun Sin(){}
private fun Cos(){}
private fun Tan(){}
private fun Cosin(){}


val Int.nonScaledSp
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp