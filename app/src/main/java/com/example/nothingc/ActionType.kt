package com.example.nothingc

import com.example.nothingc.components.buttons.CalculatorButtonComponentDesign


sealed class ActionType(val symbol: String, val calculatorButtonComponentDesign: CalculatorButtonComponentDesign) {

    data class Number(val number: Int) : ActionType(number.toString(), CalculatorButtonComponentDesign.Primary)
    data class Operator(val operation: Operators) : ActionType(operation.symbol, CalculatorButtonComponentDesign.Secondary)

    object Calculate : ActionType("=", CalculatorButtonComponentDesign.Special)
    object Delete : ActionType("<", CalculatorButtonComponentDesign.Primary)
    object Clear : ActionType("AC", CalculatorButtonComponentDesign.Secondary)
    object Decimal : ActionType(",", CalculatorButtonComponentDesign.Primary)
    object Percentage: ActionType("%", CalculatorButtonComponentDesign.Secondary)
}

sealed class Operators(val symbol: String, val calculatorButtonComponentDesign: CalculatorButtonComponentDesign) {
    object Add : Operators("+", CalculatorButtonComponentDesign.Secondary)
    object Subtract : Operators("-", CalculatorButtonComponentDesign.Secondary)
    object Multiply : Operators("X", CalculatorButtonComponentDesign.Secondary)
    object Divide : Operators("/", CalculatorButtonComponentDesign.Secondary)
    object Power : Operators("( )", CalculatorButtonComponentDesign.Secondary)
}