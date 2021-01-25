package com.example.mykotlincalculator21.mvp.model

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import com.example.mykotlincalculator21.utils.ErrorUtils
import com.example.mykotlincalculator21.utils.NumbersUtils.POSITION_ONE
import com.example.mykotlincalculator21.utils.NumbersUtils.POSITION_ZERO
import com.example.mykotlincalculator21.utils.NumbersUtils.ZERO_NUMBER_DOUBLE_TYPE
import com.example.mykotlincalculator21.operand.Operand
import com.example.mykotlincalculator21.utils.StringUtils.EMPTY_STRING
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_DIVIDE
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_MINUS
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_MULTIPLY
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_SUM

class CalculatorModel : CalculatorContract.CalculatorModelContract {
    private val firstOperandUtils = Operand()
    private val secondOperandUtils = Operand()
    private var operator: String = EMPTY_STRING
    private var result: String = EMPTY_STRING
    private var error = ErrorUtils.SUCCESS

    override fun saveNumber(number: String) {
        if (operator.isEmpty()) {
            firstOperandUtils.concatenateNumber(number)
        } else {
            secondOperandUtils.concatenateNumber(number)
        }
    }

    override fun getValue(): String {
        var firstValue = (firstOperandUtils.getValue()).toString()
        var secondValue = (secondOperandUtils.getValue()).toString()
        if (secondOperandUtils.isEmpty()) {
            secondValue = EMPTY_STRING
        }
        return "$firstValue$operator$secondValue"
    }

    private fun isValidOperation(): Boolean = when {
        operator.isEmpty() && firstOperandUtils.isEmpty() -> {
            result = EMPTY_STRING
            false
        }
        operator.isNotEmpty() -> {
            result = java.lang.String.valueOf(firstOperandUtils.getValue())
            true
        }
        firstOperandUtils.isEmpty() -> {
            result = EMPTY_STRING
            true
        }
        secondOperandUtils.isEmpty() -> {
            error = ErrorUtils.ERROR_MESSAGE_INVALID_FORMAT
            result = EMPTY_STRING
            false
        }
        else -> true
    }

    override fun doOperations() {
        if (isValidOperation()) {

            when (operator) {
                OPERATOR_SUM -> {
                    result =
                        (firstOperandUtils.getValue() + secondOperandUtils.getValue()).toString()
                    error = ErrorUtils.SUCCESS
                }
                OPERATOR_MINUS -> {
                    result =
                        (firstOperandUtils.getValue() - secondOperandUtils.getValue()).toString()
                    error = ErrorUtils.SUCCESS
                }
                OPERATOR_DIVIDE -> if (secondOperandUtils.getValue() == ZERO_NUMBER_DOUBLE_TYPE) {
                    result = EMPTY_STRING
                    error = ErrorUtils.ERROR_MESSAGE_DIVISION
                } else {
                    result =
                        (firstOperandUtils.getValue() / secondOperandUtils.getValue()).toString()
                    error = ErrorUtils.SUCCESS
                }
                OPERATOR_MULTIPLY -> {
                    result =
                        (firstOperandUtils.getValue() * secondOperandUtils.getValue()).toString()
                    error = ErrorUtils.SUCCESS
                }
                else -> {
                    ErrorUtils.ERROR_MESSAGE.toString()
                    result = EMPTY_STRING
                }
            }
            updateFirstOperand()
            secondOperandUtils.eraseOperands()
        }
    }

    override fun getResult(): String = result

    private fun updateFirstOperand() {
        if (result.substring(POSITION_ZERO).equals(OPERATOR_MINUS)) {
            firstOperandUtils.signs = OPERATOR_MINUS
            firstOperandUtils.value = result.substring(POSITION_ONE, result.length)
        } else {
            firstOperandUtils.signs = EMPTY_STRING
            firstOperandUtils.value = result
        }
        operator = EMPTY_STRING
    }

    override fun eraseResult(): String {
        result = EMPTY_STRING
        firstOperandUtils.eraseOperands()
        secondOperandUtils.eraseOperands()
        operator = EMPTY_STRING
        return EMPTY_STRING
    }

    override fun saveOperationSymbol(operatorSymbol: String) {
        if (firstOperandUtils.isEmpty()) {
            firstOperandUtils.signs = OPERATOR_MINUS
        } else if (operator.isEmpty()) {
            operator = operatorSymbol
        } else {
            secondOperandUtils.signs = OPERATOR_MINUS
        }
    }

    override fun getError() = error
}
