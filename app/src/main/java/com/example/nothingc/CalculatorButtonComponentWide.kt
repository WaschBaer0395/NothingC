package com.example.nothingc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.nothingc.ui.theme.CalculatorTheme
import com.example.nothingc.ui.theme.NothingBlack
import com.example.nothingc.ui.theme.NothingGrey
import com.example.nothingc.ui.theme.NothingRed
import com.example.nothingc.ui.theme.NothingWhite
import com.example.nothingc.ui.theme.ndot55

// Design to select for VariablesAuswahlfeld
enum class CalculatorButtonComponentWideDesign {
    Primary,
    Secondary,
    Special
}

@Composable
internal fun CalculatorButtonComponentWide(
    modifier: Modifier = Modifier,
    calculatorButtonComponentDesign: CalculatorButtonComponentDesign = CalculatorButtonComponentDesign.Primary,
    symbol: String,
    onClick: () -> Unit
) {
    when (calculatorButtonComponentDesign) {
        CalculatorButtonComponentDesign.Primary -> CalculatorButtonComponentWidePrimary(modifier = modifier, symbol = symbol, onClick = onClick, fontFamily = ndot55, fontSize = 62.scaledSp())
        CalculatorButtonComponentDesign.Secondary -> CalculatorButtonComponentWideSecondary(modifier = modifier, symbol = symbol, onClick = onClick, fontFamily = ndot55, fontSize = 62.scaledSp())
        CalculatorButtonComponentDesign.Special -> CalculatorButtonComponentWideSpecial(modifier = modifier, symbol = symbol, onClick = onClick, fontFamily = ndot55, fontSize = 62.scaledSp())
    }
}

@Composable
internal fun CalculatorButtonComponentWideSpecial(
    modifier: Modifier = Modifier,
    symbol: String,
    fontSize: TextUnit,
    fontFamily: FontFamily,
    onClick: () -> Unit
) {
    CalculatorTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .clip(RoundedCornerShape(50.scaledDp()))
                .background(NothingRed)
                .clickable { onClick() }
                .then(modifier)
        ){
            Text(
                modifier = Modifier.padding(end = 0.5.dp),
                textAlign = TextAlign.Center,
                color = NothingWhite,
                text = symbol,
                fontSize = fontSize,
                fontFamily = fontFamily,
            )
        }
    }
}

@Composable
internal fun CalculatorButtonComponentWidePrimary(
    modifier: Modifier = Modifier,
    symbol: String,
    fontSize: TextUnit,
    fontFamily: FontFamily,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(50.scaledDp()))
            .background(if (isSystemInDarkTheme()) NothingGrey else NothingWhite)
            .clickable { onClick() }
            .then(modifier)
    ){
        Text(
            modifier = Modifier.padding(end = 0.5.dp),
            textAlign = TextAlign.Center,
            color = if (isSystemInDarkTheme()) NothingWhite else NothingBlack,
            text = symbol,
            fontSize = fontSize,
            fontFamily = fontFamily,
        )
    }
}

@Composable
internal fun CalculatorButtonComponentWideSecondary(
    modifier: Modifier = Modifier,
    symbol: String,
    fontSize: TextUnit,
    fontFamily: FontFamily,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(50.scaledDp()))
            .background(if (isSystemInDarkTheme()) NothingWhite else NothingGrey)
            .clickable { onClick() }
            .then(modifier)
    ){
        Text(
            modifier = Modifier.padding(end = 0.5.dp),
            textAlign = TextAlign.Center,
            color = if (isSystemInDarkTheme()) NothingBlack else NothingWhite,
            text = symbol,
            fontSize = fontSize,
            fontFamily = fontFamily,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonComponentWidePrimaryPreview(){
    CalculatorTheme {
        CalculatorButtonComponent(
            modifier = Modifier.size(width = 200.scaledDp(), height = 100.scaledDp()),
            calculatorButtonComponentDesign = CalculatorButtonComponentDesign.Primary,
            symbol = " log ",
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonComponentWideSecondaryPreview(){
    CalculatorTheme {
        CalculatorButtonComponent(
            modifier = Modifier.size(width = 200.scaledDp(), height = 100.scaledDp()),
            calculatorButtonComponentDesign = CalculatorButtonComponentDesign.Secondary,
            symbol = "Sin ( )",
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonComponentWideSpecialPreview(){
    CalculatorTheme {
        CalculatorButtonComponent(
            modifier = Modifier.size(width = 200.scaledDp(), height = 100.scaledDp()),
            calculatorButtonComponentDesign = CalculatorButtonComponentDesign.Special,
            symbol = " = ",
            onClick = {},
        )
    }
}

