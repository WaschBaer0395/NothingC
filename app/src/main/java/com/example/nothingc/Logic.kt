package com.example.nothingc


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.log
import kotlin.math.log10
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan


fun Add(num1: Double, num2: Double) : Double{
    return num1 + num2
}
fun Subtract(num1: Double, num2: Double) : Double{
    return num1 - num2
}
fun Multiply(num1: Double, num2: Double) : Double{
    return num1 * num2
}
fun Divide(num1: Double, num2: Double) : Double{
    return num1 / num2
}
fun Power(num1: Double, num2: Double) : Double{
    return num1.pow(num2)
}
fun Percentage(num1: Double, num2: Double) : Double{
    return (num2/100)*num1
}
fun Root(num1: Double) : Double{
    return sqrt(num1)
}
fun Sin(num1: Double) : Double{
    return sin(num1)
}
fun Cos(num1: Double) : Double{
    return cos(num1)
}
fun Tan(num1: Double) : Double{
    return tan(num1)
}
fun Log10(num1: Double) : Double{
    return log10(num1)
}

fun Log2(num1: Double) : Double{
    return log2(num1)
}

fun Log(num1: Double, num2: Double) : Double{
    return log(num1, num2)
}


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