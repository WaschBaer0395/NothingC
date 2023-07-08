package com.example.nothingc.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import com.example.nothingc.ui.theme.CalculatorTheme
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
import com.example.nothingc.ui.theme.NothingBlack
import com.example.nothingc.ui.theme.NothingGrey
import com.example.nothingc.ui.theme.NothingRed
import com.example.nothingc.ui.theme.NothingWhite
import com.example.nothingc.ui.theme.appWideFont
import com.example.nothingc.ui.theme.buttonFontSize

// Design to select for VariablesAuswahlfeld
enum class CalculatorButtonComponentDesign {
    Primary,
    Secondary,
    Special
}



@Composable
internal fun CalculatorButtonComponent(
    modifier: Modifier = Modifier,
    calculatorButtonComponentDesign: CalculatorButtonComponentDesign = CalculatorButtonComponentDesign.Primary,
    symbol: String,
    onClick: () -> Unit
) {



    when (calculatorButtonComponentDesign) {
        CalculatorButtonComponentDesign.Primary -> CalculatorButtonComponentPrimary(modifier = modifier, symbol = symbol, onClick = onClick, fontSize = buttonFontSize.scaledSp())
        CalculatorButtonComponentDesign.Secondary -> CalculatorButtonComponentSecondary(modifier = modifier, symbol = symbol, onClick = onClick, fontSize = buttonFontSize.scaledSp())
        CalculatorButtonComponentDesign.Special -> CalculatorButtonComponentSpecial(modifier = modifier, symbol = symbol, onClick = onClick, fontSize = buttonFontSize.scaledSp())
    }
}

@Composable
internal fun CalculatorButtonComponentSpecial(
    modifier: Modifier = Modifier,
    symbol: String,
    fontSize: TextUnit,
    onClick: () -> Unit
) {
    CalculatorTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .clip(CircleShape)
                .background(NothingRed)
                .clickable { onClick() }
                .then(modifier)
        ){
            Text(
                text = symbol,
                modifier = Modifier
                    .padding(end = 0.5.dp),
                textAlign = TextAlign.Center,
                color = NothingWhite,
                fontSize = fontSize,
                fontFamily = appWideFont,
            )
        }
    }
}

@Composable
internal fun CalculatorButtonComponentPrimary(
    modifier: Modifier = Modifier,
    symbol: String,
    fontSize: TextUnit,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(if (isSystemInDarkTheme()) NothingGrey else NothingWhite)
            .clickable { onClick() }
            .then(modifier)
    ){
        Text(
            text = symbol,
            modifier = Modifier.padding(end = 0.5.dp),
            textAlign = TextAlign.Center,
            color = if (isSystemInDarkTheme()) NothingWhite else NothingBlack,
            fontSize = fontSize,
            fontFamily = appWideFont,
        )
    }
}

@Composable
internal fun CalculatorButtonComponentSecondary(
    modifier: Modifier = Modifier,
    symbol: String,
    fontSize: TextUnit,
    onClick: () -> Unit
    ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(if (isSystemInDarkTheme()) NothingWhite else NothingGrey)
            .clickable { onClick() }
            .then(modifier)
    ){
        Text(
            text = symbol,
            modifier = Modifier.padding(end = 0.5.dp),
            textAlign = TextAlign.Center,
            color = if (isSystemInDarkTheme()) NothingBlack else NothingWhite,
            fontSize = fontSize,
            fontFamily = appWideFont,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonComponentPreviewPrimary(){
    CalculatorTheme {
        CalculatorButtonComponent(
            modifier = Modifier.size(100.scaledDp()),
            calculatorButtonComponentDesign = CalculatorButtonComponentDesign.Primary,
            symbol = " 1 ",
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonComponentPreviewSecondary(){
    CalculatorTheme {
        CalculatorButtonComponent(
            modifier = Modifier.size(100.scaledDp()),
            calculatorButtonComponentDesign = CalculatorButtonComponentDesign.Secondary,
            symbol = " + ",
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorButtonComponentPreviewSpecial(){
    CalculatorTheme {
        CalculatorButtonComponent(
            modifier = Modifier.size(100.scaledDp()),
            calculatorButtonComponentDesign = CalculatorButtonComponentDesign.Special,
            symbol = " = ",
            onClick = {},
        )
    }
}