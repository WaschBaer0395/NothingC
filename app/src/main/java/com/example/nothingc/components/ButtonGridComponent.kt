package com.example.nothingc.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nothingc.ActionType
import com.example.nothingc.Operators
import com.example.nothingc.components.buttons.CalculatorButtonComponent
import com.example.nothingc.scaledDp
import com.example.nothingc.ui.theme.spacing

@Composable
fun CalculatorButtonsGridLayout(modifier: Modifier = Modifier, dispatcher: (ActionType) -> Unit) {
    val buttons = listOf(
        ActionType.Clear,
        ActionType.Operator(Operators.Power),
        ActionType.Percentage,
        ActionType.Operator(Operators.Divide),
        ActionType.Number(7),
        ActionType.Number(8),
        ActionType.Number(9),
        ActionType.Operator(Operators.Multiply),
        ActionType.Number(4),
        ActionType.Number(5),
        ActionType.Number(6),
        ActionType.Operator(Operators.Subtract),
        ActionType.Number(3),
        ActionType.Number(2),
        ActionType.Number(1),
        ActionType.Operator(Operators.Add),
        ActionType.Number(0),
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
                    symbol = it.symbol
                ) {
                    dispatcher(it)
                }
            }
        })
}