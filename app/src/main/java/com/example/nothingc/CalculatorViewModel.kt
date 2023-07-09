package com.example.nothingc


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlin.math.pow

class CalculatorViewModel : ViewModel() {

    private val state: MutableStateFlow<State> = MutableStateFlow(State())
    internal val viewState = state
        .map { state ->
            val num1 = state.num1.ifEmpty { "" }
            val operator = state.operator?.symbol.orEmpty()
            val num2 = state.num2
            val result = state.result

            ViewState(num1, operator, num2, result)
        }

    fun dispatch(action: ActionType) {
        when (action) {
            is ActionType.Number -> onNumberClicked(action.number.toInt())
            is ActionType.Pi -> onSpecialClicked(action.pi)
            is ActionType.Euler -> onSpecialClicked(action.euler)
            is ActionType.Calculate -> onCalculateClicked()
            is ActionType.Clear -> onClearClicked()
            is ActionType.Decimal -> onDecimalClicked()
            is ActionType.Delete -> onDeleteClicked()
            is ActionType.Operator -> onOperatorClicked(action.operation)
            is ActionType.Percentage -> {}
        }
    }

    private fun onOperatorClicked(operator: Operators) {
        val currentState = state.value

        if (currentState.num1.isNotEmpty() && currentState.operator == null) {
            state.value = currentState.copy(operator = operator)
        }
    }

    private fun onDeleteClicked() {
        val currentState = state.value

        if (currentState.operator == null) {
            state.value = currentState.copy(num1 = currentState.num1.dropLast(1))
        } else if (currentState.num2.isEmpty()) {
            state.value = currentState.copy(operator = null)
        } else {
            state.value = currentState.copy(num2 = currentState.num2.dropLast(1))
        }
    }

    private fun onDecimalClicked(){
        val currentState = state.value

        if (currentState.operator == null &&
            currentState.num2.isEmpty() &&
            !currentState.num1.contains(".")
        ) {
            state.value = currentState.copy(num1 = currentState.num1 + ".")
        } else if (currentState.operator != null &&
            currentState.num2.isNotEmpty() &&
            !currentState.num2.contains(".")
        ) {
            state.value = currentState.copy(num2 = currentState.num2 + ".")
        }
    }

    private fun onClearClicked() {
        val currentState = state.value

        state.value = currentState.copy(num1 = "", num2 = "", operator = null)
    }

    private fun onCalculateClicked() {
        val currentState = state.value

        if (currentState.num1.isNotEmpty() && currentState.num2.isNotEmpty() && currentState.operator != null) {
            val num1 = currentState.num1.toInt()
            val num2 = currentState.num2.toInt()

            val result = when (currentState.operator) {
                Operators.Add -> num1 + num2
                Operators.Subtract -> num1 - num2
                Operators.Multiply -> num1 * num2
                Operators.Divide -> num1 / num2
                Operators.Power -> num1.toDouble().pow(num2.toDouble())
                else -> {}
            }

            state.value = currentState.copy(result = "$result =", num1 = "",num2 = "", operator = null)
        }
    }

    private fun onNumberClicked(number: Int) {
        val currentState = state.value

        if (currentState.operator == null) {
            state.value = currentState.copy(num1 = currentState.num1 + number)
        } else {
            state.value = currentState.copy(num2 = currentState.num2 + number)
        }
    }

    private fun onSpecialClicked(number: Double) {
        val currentState = state.value

        if (currentState.operator == null) {
            state.value = currentState.copy(num1 = currentState.num1 + number)
        } else {
            state.value = currentState.copy(num2 = currentState.num2 + number)
        }
    }

    internal class ViewState(val num1: String, val operator: String, val num2: String, val result: String)

    private data class State(
        val num1: String = "",
        val operator: Operators? = null,
        val num2: String = "",
        val result: String = ""
    )
}