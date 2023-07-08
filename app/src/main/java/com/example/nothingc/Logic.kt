package com.example.nothingc


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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


@Composable
fun Int.scaledSp(): TextUnit {
    val value: Float = ((2.625/LocalDensity.current.density)*this).toFloat()

    return with(LocalDensity.current) {
        val fontScale = this.fontScale
        val textSize =  value / fontScale
        textSize.sp
    }
}

@Composable
fun Int.scaledDp(): Dp {
    val value: Float = ((2.625/LocalDensity.current.density)*this).toFloat()
    return value.dp
}