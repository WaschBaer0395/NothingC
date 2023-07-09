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
import com.example.nothingc.Operators
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
        ActionType.Operator(Operators.Power),
        ActionType.Percentage,
        ActionType.Operator(Operators.Divide),
        // start row 2
        ActionType.Number((7).toDouble()),
        ActionType.Number((8).toDouble()),
        ActionType.Number((9).toDouble()),
        ActionType.Operator(Operators.Multiply),
        // start row 3
        ActionType.Number((4).toDouble()),
        ActionType.Number((5).toDouble()),
        ActionType.Number((6).toDouble()),
        ActionType.Operator(Operators.Subtract),
        // start row 4
        ActionType.Number((1).toDouble()),
        ActionType.Number((2).toDouble()),
        ActionType.Number((3).toDouble()),
        ActionType.Operator(Operators.Add),
        // start row 5
        ActionType.Number((0).toDouble()),
        ActionType.Decimal,
        ActionType.Delete,
        ActionType.Calculate,
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
        ActionType.Operator(Operators.Deg),
        ActionType.Operator(Operators.Inv),
        ActionType.Operator(Operators.Sin),
        ActionType.Euler(),
        // start colum 2
        ActionType.Operator(Operators.Sqrt),
        ActionType.Operator(Operators.Power),
        ActionType.Operator(Operators.Cos),
        ActionType.Operator(Operators.Ln),
        // start colum 3
        ActionType.Pi(),
        ActionType.Operator(Operators.Faculty),
        ActionType.Operator(Operators.Tan),
        ActionType.Operator(Operators.Log),
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
        ActionType.Operator(Operators.Divide),
        ActionType.Operator(Operators.Multiply),
        ActionType.Operator(Operators.Subtract),
        ActionType.Operator(Operators.Add),
        // start colum 8
        ActionType.Clear,
        ActionType.Operator(Operators.Brackets),
        ActionType.Percentage,
        ActionType.Calculate,
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