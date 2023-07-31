package com.example.nothingc.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.nothingc.ActionType
//import com.example.nothingc.Operators
import com.example.nothingc.components.buttons.CalculatorButtonComponent
import com.example.nothingc.components.buttons.CalculatorButtonComponentWide
import com.example.nothingc.scaledDp
import com.example.nothingc.scaledSp
import com.example.nothingc.ui.theme.buttonFontSizeH
import com.example.nothingc.ui.theme.buttonFontSizeV
import com.example.nothingc.ui.theme.spacing

@Composable
fun CalcButtGridLayoutV(modifier: Modifier = Modifier, dispatcher: (ActionType) -> Unit) {
    val buttons = listOf(
        // from top to bottom
        // left to right
        // start row 1
        ActionType.Clear,
        ActionType.Operator("^"),
        ActionType.Percentage,
        ActionType.Operator("/"),
        // start row 2
        ActionType.Number((7).toDouble()),
        ActionType.Number((8).toDouble()),
        ActionType.Number((9).toDouble()),
        ActionType.Operator("*"),
        // start row 3
        ActionType.Number((4).toDouble()),
        ActionType.Number((5).toDouble()),
        ActionType.Number((6).toDouble()),
        ActionType.Operator("-"),
        // start row 4
        ActionType.Number((1).toDouble()),
        ActionType.Number((2).toDouble()),
        ActionType.Number((3).toDouble()),
        ActionType.Operator("+"),
        // start row 5
        ActionType.Number((0).toDouble()),
        ActionType.Decimal,
        ActionType.Delete,
        ActionType.Delete,
    )

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm.scaledDp()),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm.scaledDp()),
        content = {
            items(buttons) {
                CalculatorButtonComponent(
                    modifier = Modifier.aspectRatio(1f),
                    calculatorButtonComponentDesign = it.calculatorButtonComponentDesign,
                    symbol = it.symbol,
                    fontSize = buttonFontSizeV.scaledSp()
                ) {
                    dispatcher(it)
                }
            }
        })
}

@Composable
fun CalcButtGridLayoutH(modifier: Modifier = Modifier, dispatcher: (ActionType) -> Unit) {
    val buttons = listOf(
        // from left to right
        // top to bottom
        // start column 1
        ActionType.Operator("Deg"),
        ActionType.Operator("Inv"),
        ActionType.Operator("sin("),
        ActionType.Euler(),
        // start colum 2
        ActionType.Operator("sqrt("),
        ActionType.Operator("^"),
        ActionType.Operator("cos("),
        ActionType.Operator("LN"),
        // start colum 3
        ActionType.Pi(),
        ActionType.Operator("!"),
        ActionType.Operator("tan("),
        ActionType.Operator("log"),
        // start colum 4
        ActionType.Number((7).toDouble()),
        ActionType.Number((4).toDouble()),
        ActionType.Number((1).toDouble()),
        ActionType.Number((0).toDouble()),
        // start colum 5
        ActionType.Number((8).toDouble()),
        ActionType.Number((5).toDouble()),
        ActionType.Number((2).toDouble()),
        ActionType.Decimal,
        // start colum 6
        ActionType.Number((9).toDouble()),
        ActionType.Number((6).toDouble()),
        ActionType.Number((3).toDouble()),
        ActionType.Delete,
        // start colum 7
        ActionType.Operator("/"),
        ActionType.Operator("*"),
        ActionType.Operator("-"),
        ActionType.Operator("+"),
        // start colum 8
        ActionType.Clear,
        ActionType.Operator(")"),
        ActionType.Percentage,
        ActionType.Clear,
        //
        )

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    LazyHorizontalGrid(
        modifier = modifier
            .height((screenHeight/7)*4)
            .fillMaxWidth(),
        rows = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm.scaledDp()),
        horizontalArrangement = Arrangement.spacedBy(10.scaledDp()),
        content = {
            items(buttons) {
                CalculatorButtonComponentWide(
                    modifier = Modifier.aspectRatio(2.4f),
                    calculatorButtonComponentDesign = it.calculatorButtonComponentDesign,
                    symbol = it.symbol,
                    fontSize = buttonFontSizeH.scaledSp(),
                ) {
                    dispatcher(it)
                }
            }
        })
}