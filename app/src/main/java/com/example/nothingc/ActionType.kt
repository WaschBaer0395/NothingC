package com.example.nothingc

import com.example.nothingc.components.buttons.CalculatorButtonComponentDesign


sealed class ActionType(val symbol: String, val calculatorButtonComponentDesign: CalculatorButtonComponentDesign) {

    data class Number(val number: Double) : ActionType(number.toInt().toString(), CalculatorButtonComponentDesign.Primary)
    data class Operator(val operator: String) : ActionType(operator, CalculatorButtonComponentDesign.Secondary)
    data class Pi(val pi: Double = Math.PI) : ActionType("π", CalculatorButtonComponentDesign.Secondary)
    data class Euler(val euler: Double = Math.E) : ActionType("e", CalculatorButtonComponentDesign.Secondary)


    //object Calculate : ActionType("=", CalculatorButtonComponentDesign.Special)
    object Delete : ActionType("Del", CalculatorButtonComponentDesign.Primary)
    object Clear : ActionType("AC", CalculatorButtonComponentDesign.Secondary)
    object Decimal : ActionType(",", CalculatorButtonComponentDesign.Primary)
    object Percentage: ActionType("%", CalculatorButtonComponentDesign.Secondary)
}