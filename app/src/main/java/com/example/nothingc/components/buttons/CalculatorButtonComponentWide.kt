package com.example.nothingc.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.nothingc.scaledDp
import com.example.nothingc.scaledSp
import com.example.nothingc.ui.theme.CalculatorTheme
import com.example.nothingc.ui.theme.NothingBlack
import com.example.nothingc.ui.theme.NothingGrey
import com.example.nothingc.ui.theme.NothingRed
import com.example.nothingc.ui.theme.NothingWhite
import com.example.nothingc.ui.theme.appWideFont
import com.example.nothingc.ui.theme.buttonFontSizeH

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
    fontSize: TextUnit,
    onClick: () -> Unit
) {
    when (calculatorButtonComponentDesign) {
        CalculatorButtonComponentDesign.Primary -> CalculatorButtonComponentWidePrimary(modifier = modifier, symbol = symbol, onClick = onClick, fontSize = fontSize)
        CalculatorButtonComponentDesign.Secondary -> CalculatorButtonComponentWideSecondary(modifier = modifier, symbol = symbol, onClick = onClick, fontSize = fontSize)
        CalculatorButtonComponentDesign.Special -> CalculatorButtonComponentWideSpecial(modifier = modifier, symbol = symbol, onClick = onClick, fontSize = fontSize)
    }
}

@Composable
internal fun CalculatorButtonComponentWideSpecial(
    modifier: Modifier = Modifier,
    symbol: String,
    fontSize: TextUnit,
    onClick: () -> Unit
) {
    CalculatorTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .clip(RoundedCornerShape(50.scaledDp()))
                .background(MaterialTheme.colors.error)
                .clickable { onClick() }
                .then(modifier)
        ){
            Text(
                modifier = Modifier.padding(end = 0.5.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onError,
                text = symbol,
                fontSize = fontSize,
                fontFamily = appWideFont,
            )
        }
    }
}

@Composable
internal fun CalculatorButtonComponentWidePrimary(
    modifier: Modifier = Modifier,
    symbol: String,
    fontSize: TextUnit,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(50.scaledDp()))
            .background(MaterialTheme.colors.primary)
            .clickable { onClick() }
            .then(modifier)
    ){
        Text(
            modifier = Modifier.padding(end = 0.5.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
            text = symbol,
            fontSize = fontSize,
            fontFamily = appWideFont,
        )
    }
}

@Composable
internal fun CalculatorButtonComponentWideSecondary(
    modifier: Modifier = Modifier,
    symbol: String,
    fontSize: TextUnit,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(50.scaledDp()))
            .background(MaterialTheme.colors.secondary)
            .clickable { onClick() }
            .then(modifier)
    ){
        Text(
            modifier = Modifier.padding(end = 0.5.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSecondary,
            text = symbol,
            fontSize = fontSize,
            fontFamily = appWideFont,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonComponentWidePrimaryPreview(){
    CalculatorTheme {
        CalculatorButtonComponent(
            modifier = Modifier.size(width = 100.scaledDp(), height = 50.scaledDp()),
            calculatorButtonComponentDesign = CalculatorButtonComponentDesign.Primary,
            symbol = " log ",
            fontSize = buttonFontSizeH.scaledSp(),
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonComponentWideSecondaryPreview(){
    CalculatorTheme {
        CalculatorButtonComponent(
            modifier = Modifier.size(width = 100.scaledDp(), height = 50.scaledDp()),
            calculatorButtonComponentDesign = CalculatorButtonComponentDesign.Secondary,
            symbol = "Sin ( )",
            fontSize = buttonFontSizeH.scaledSp(),
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonComponentWideSpecialPreview(){
    CalculatorTheme {
        CalculatorButtonComponent(
            modifier = Modifier.size(width = 100.scaledDp(), height = 50.scaledDp()),
            calculatorButtonComponentDesign = CalculatorButtonComponentDesign.Special,
            symbol = " = ",
            fontSize = buttonFontSizeH.scaledSp(),
            onClick = {},
        )
    }
}

