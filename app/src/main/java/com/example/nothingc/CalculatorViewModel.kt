package com.example.nothingc


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.lang.String.format
import java.util.Stack

class CalculatorViewModel : ViewModel() {

    private val state: MutableStateFlow<State> = MutableStateFlow(State())
    internal val viewState = state
        .map { state ->
            val inputStack = Stack<String>()
            val operator = state.operator.orEmpty()
            val num2 = state.num2
            val result = state.result

            ViewState(inputStack, operator, num2, result)
        }

    fun dispatch(action: ActionType) {
        when (action) {
            is ActionType.Number -> onInputGiven(action.number.toInt().toString())
            is ActionType.Pi -> onSpecialClicked(action.pi)
            is ActionType.Euler -> onSpecialClicked(action.euler)
//            is ActionType.Calculate -> onCalculateClicked()
            is ActionType.Clear -> onClearClicked()
            is ActionType.Decimal -> onDecimalClicked()
            is ActionType.Delete -> onDeleteClicked()
            is ActionType.Operator -> onInputGiven(action.operator)
            is ActionType.Percentage -> {}
        }
    }

    private fun onInputGiven(input: String) {
        val currentState = state.value
        val stack = Stack<String>()

        if (input.isNumeric()) {
            stack.push(input)
        } else if (input.isOperator()) {
            if (isValidOperatorOrder(input)) {
                val num2 = stack.pop().toDoubleOrNull()
                val num1 = stack.pop().toDoubleOrNull()

                if (num1 != null && num2 != null) {
                    val result = performOperation(num1, num2, input)
                    stack.push(result.toString())
                    state.value = currentState.copy(result = result.toString(),inputStack = stack)
                    println("Current result: $result")
                } else {
                    println("Invalid input order. Try again.")
                }
            } else {
                println("Invalid operator order. Try again.")
            }
        } else {
            println("Invalid input. Try again.")
        }
        state.value = currentState.copy(inputStack = stack)
    }

    private fun String.isNumeric(): Boolean {
        return this.matches("-?\\d+(\\.\\d+)?".toRegex())
    }

    private fun String.isOperator(): Boolean {
        return this == "+" || this == "-" || this == "*" || this == "/"
    }

    private fun isValidOperatorOrder(operator: String): Boolean {
        return operator != "*" && operator != "/"
    }

    private fun performOperation(num1: Double, num2: Double, operator: String): Double {
        return when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num1 / num2
            else -> 0.0
        }
    }

//    private fun onOperatorClicked(operator: Operators) {
//        val currentState = state.value
//
//        if (currentState.num1.isNotEmpty() && currentState.operator == null) {
//            state.value = currentState.copy(operator = operator)
//        }
//    }


    private fun onDecimalClicked(){
//        val currentState = state.value
//
//        if (currentState.operator == null &&
//            currentState.num2.isEmpty() &&
//            !currentState.num1.contains(".")
//        ) {
//            state.value = currentState.copy(num1 = currentState.num1 + ".")
//        } else if (currentState.operator != null &&
//            currentState.num2.isNotEmpty() &&
//            !currentState.num2.contains(".")
//        ) {
//            state.value = currentState.copy(num2 = currentState.num2 + ".")
//        }
    }

    private fun onDeleteClicked() {
//        val currentState = state.value
//
//        if (currentState.operator == null) {
//            state.value = currentState.copy(num1 = currentState.num1.dropLast(1))
//        } else if (currentState.num2.isEmpty()) {
//            state.value = currentState.copy(operator = null)
//        } else {
//            state.value = currentState.copy(num2 = currentState.num2.dropLast(1))
//        }
    }


    private fun onClearClicked() {
//        val currentState = state.value
//
//        state.value = currentState.copy(num1 = "", num2 = "", operator = null)
    }

//    private fun onCalculateClicked() {
//        val currentState = state.value
//
//        if (currentState.num1.isNotEmpty() && currentState.num2.isNotEmpty() && currentState.operator != null) {
//            val num1 = currentState.num1.toDouble()
//            val num2 = currentState.num2.toDouble()
//
//            android.util.Log.d(TAG, currentState.operator.toString())
//            val result = when (currentState.operator) {
//                Operators.Add -> num1 + num2
//                Operators.Subtract -> num1 - num2
//                Operators.Multiply -> num1 * num2
//                Operators.Divide -> if (num2 == 0.0) "Nothing" else num1 / num2
//                Operators.Power -> num1.toDouble().pow(num2.toDouble())
//                else -> {}
//            }
//
//            state.value = currentState.copy(result = "$result =", num1 = "",num2 = "", operator = null)
//        }
//    }

    private fun onNumberClicked(number: Int){
//        val currentState = state.value
//        state.value = currentState.copy(num1 = currentState.num1 + " " + number.toString())
//        println(format("Number Clicked new formula is: %s", currentState.num1))
    }

//    private fun onNumberClicked(number: Int) {
//        val currentState = state.value
//
//        if (currentState.operator == null) {
//            state.value = currentState.copy(num1 = currentState.num1 + number)
//        } else {
//            state.value = currentState.copy(num2 = currentState.num2 + number)
//        }
//    }

    private fun onSpecialClicked(number: Double) {
//        val currentState = state.value
//
//        if (currentState.operator == null) {
//            state.value = currentState.copy(num1 = currentState.num1 + number)
//        } else {
//            state.value = currentState.copy(num2 = currentState.num2 + number)
//        }
    }

    internal class ViewState(val inputStack: Stack<String>, val operator: String, val num2: String, val result: String)

    private data class State(
        val inputStack: Stack<String> = Stack<String>(),
        val operator: String? = null,
        val num2: String = "",
        val result: String = ""
    )
}