package com.example.nothingc

import com.example.nothingc.components.buttons.CalculatorButtonComponentDesign


sealed class ActionType(val symbol: String, val calculatorButtonComponentDesign: CalculatorButtonComponentDesign) {

    data class Number(val number: Double) : ActionType(number.toInt().toString(), CalculatorButtonComponentDesign.Primary)
    data class Operator(val operation: Operators) : ActionType(operation.symbol, CalculatorButtonComponentDesign.Secondary)
    data class Pi(val pi: Double = Math.PI) : ActionType("Pi", CalculatorButtonComponentDesign.Secondary)
    data class Euler(val euler: Double = Math.E) : ActionType("e", CalculatorButtonComponentDesign.Secondary)


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
    object Brackets : Operators("( )", CalculatorButtonComponentDesign.Secondary)
    object Log : Operators("log", CalculatorButtonComponentDesign.Secondary)
    object Tan : Operators("tan", CalculatorButtonComponentDesign.Secondary)
    object Sin : Operators("sin", CalculatorButtonComponentDesign.Secondary)
    object Cos : Operators("cos", CalculatorButtonComponentDesign.Secondary)
    object Faculty : Operators("!", CalculatorButtonComponentDesign.Secondary)
    object Power : Operators("^", CalculatorButtonComponentDesign.Secondary)
    object Ln : Operators("ln", CalculatorButtonComponentDesign.Secondary)
    object Sqrt : Operators("sqrt", CalculatorButtonComponentDesign.Secondary)
    object Deg : Operators("Deg", CalculatorButtonComponentDesign.Secondary)
    object Rad : Operators("Rad", CalculatorButtonComponentDesign.Secondary)
    object Inv : Operators("Inv", CalculatorButtonComponentDesign.Secondary)







}