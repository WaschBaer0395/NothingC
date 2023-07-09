package com.example.nothingc.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
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
import com.example.nothingc.ui.theme.appWideFont
import com.example.nothingc.ui.theme.buttonFontSizeV

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
    fontSize: TextUnit,
    onClick: () -> Unit
) {



    when (calculatorButtonComponentDesign) {
        CalculatorButtonComponentDesign.Primary -> CalculatorButtonComponentPrimary(modifier = modifier, symbol = symbol, onClick = onClick, fontSize = fontSize)
        CalculatorButtonComponentDesign.Secondary -> CalculatorButtonComponentSecondary(modifier = modifier, symbol = symbol, onClick = onClick, fontSize = fontSize)
        CalculatorButtonComponentDesign.Special -> CalculatorButtonComponentSpecial(modifier = modifier, symbol = symbol, onClick = onClick, fontSize = fontSize)
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
                .background(MaterialTheme.colors.error)
                .clickable { onClick() }
                .then(modifier)
        ){
            Text(
                text = symbol,
                modifier = Modifier
                    .padding(end = 0.5.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onError,
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
            .background(MaterialTheme.colors.primary)
            .clickable { onClick() }
            .then(modifier)
    ){
        Text(
            text = symbol,
            modifier = Modifier.padding(end = 0.5.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
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
            .background(MaterialTheme.colors.secondary)
            .clickable { onClick() }
            .then(modifier)
    ){
        Text(
            text = symbol,
            modifier = Modifier.padding(end = 0.5.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSecondary,
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
            fontSize = buttonFontSizeV.scaledSp(),
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
            fontSize = buttonFontSizeV.scaledSp(),
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
            fontSize = buttonFontSizeV.scaledSp(),
            onClick = {},
        )
    }
}