package com.example.mykotlincalculator21.mvp.model

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import com.example.mykotlincalculator21.utils.ErrorUtils
import com.example.mykotlincalculator21.utils.NumbersUtils.POSITION_ONE
import com.example.mykotlincalculator21.utils.NumbersUtils.POSITION_ZERO
import com.example.mykotlincalculator21.utils.NumbersUtils.ZERO_NUMBER_DOUBLE_TYPE
import com.example.mykotlincalculator21.utils.OperandUtils
import com.example.mykotlincalculator21.utils.StringUtils.EMPTY_STRING
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_DIVIDE
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_MINUS
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_MULTIPLY
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_SUM

class CalculatorModel : CalculatorContract.CalculatorModelContract {
    private val firstOperandUtils = OperandUtils()
    private val secondOperandUtils = OperandUtils()
    private var operator: String = EMPTY_STRING
    private var result: String = EMPTY_STRING
    private var error = ErrorUtils.NONE

    override fun saveNumber(number: String) {
        if (operator.isEmpty()) {
            firstOperandUtils.addNumber(number)
        } else {
            secondOperandUtils.addNumber(number)
        }
    }

    fun getValue(): String {
        var firstValue = (firstOperandUtils.getValue()).toString()
        var secondValue = (secondOperandUtils.getValue()).toString()
        if (secondOperandUtils.isEmpty()) {
            secondValue = EMPTY_STRING
        }
        return "$firstValue$operator$secondValue"
    }

    override fun getPartialResult(): String {
        return getValue()
    }

    private fun isValidOperation(): Boolean {
        if (operator.isEmpty()) {
            if (firstOperandUtils.isEmpty()) {
                result = EMPTY_STRING
            } else {
                result = java.lang.String.valueOf(firstOperandUtils.getValue())
            }
            return false
        } else if (secondOperandUtils.isEmpty()) {
            error = ErrorUtils.ERROR_MESSAGE_INVALID_FORMAT
            result = EMPTY_STRING
            return false
        }
        return true
    }

    override fun doOperations() {
        if (isValidOperation()) {

            when (operator) {
                OPERATOR_SUM -> {
                    result =
                        (firstOperandUtils.getValue() + secondOperandUtils.getValue()).toString()
                    error = ErrorUtils.NONE
                }
                OPERATOR_MINUS -> {
                    result =
                        (firstOperandUtils.getValue() - secondOperandUtils.getValue()).toString()
                    error = ErrorUtils.NONE
                }
                OPERATOR_DIVIDE -> if (secondOperandUtils.getValue() == ZERO_NUMBER_DOUBLE_TYPE) {
                    result = EMPTY_STRING
                    error = ErrorUtils.ERROR_MESSAGE_DIVISION
                } else {
                    result =
                        (firstOperandUtils.getValue() / secondOperandUtils.getValue()).toString()
                    error = ErrorUtils.NONE
                }
                OPERATOR_MULTIPLY -> {
                    result =
                        (firstOperandUtils.getValue() * secondOperandUtils.getValue()).toString()
                    error = ErrorUtils.NONE
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

    override fun getResult(): String {
        return result
    }

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
            firstOperandUtils.setSign(OPERATOR_MINUS)
        } else if (operator.isEmpty()) {
            operator = operatorSymbol
        } else {
            secondOperandUtils.setSign(OPERATOR_MINUS)
        }
    }

    override fun getError(): ErrorUtils {
        return error
    }

}
