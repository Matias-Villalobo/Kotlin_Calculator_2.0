package com.example.mykotlincalculator21.mvp.model

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import com.example.mykotlincalculator21.utils.ResultUtils
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
    private var resultOperation: String = EMPTY_STRING
    private var result = ResultUtils.SUCCESS

    override fun saveNumber(number: String) {
        if (operator.isEmpty()) {
            firstOperandUtils.addNumber(number)
        } else {
            secondOperandUtils.addNumber(number)
        }
    }

    override fun getValue(): String {
        var firstValue: String = (firstOperandUtils.getValue()).toString()
        var secondValue: String = (secondOperandUtils.getValue()).toString()
        if (secondOperandUtils.isEmpty()) {
            secondValue = EMPTY_STRING
        }
        return "$firstValue$operator$secondValue"
    }

    private fun isValidOperation(): Boolean = when {
        operator.isEmpty() && firstOperandUtils.isEmpty() -> {
            resultOperation = EMPTY_STRING
            false
        }
        operator.isNotEmpty() -> {
            resultOperation = java.lang.String.valueOf(firstOperandUtils.getValue())
            true
        }
        firstOperandUtils.isEmpty() -> {
            resultOperation = EMPTY_STRING
            true
        }
        secondOperandUtils.isEmpty() -> {
            result = ResultUtils.ERROR_MESSAGE_INVALID_FORMAT
            resultOperation = EMPTY_STRING
            false
        }
        else -> true
    }

    override fun doOperations() {
        if (isValidOperation()) {

            when (operator) {
                OPERATOR_SUM -> {
                    resultOperation =
                        (firstOperandUtils.getValue() + secondOperandUtils.getValue()).toString()
                    result = ResultUtils.SUCCESS
                }
                OPERATOR_MINUS -> {
                    resultOperation =
                        (firstOperandUtils.getValue() - secondOperandUtils.getValue()).toString()
                    result = ResultUtils.SUCCESS
                }
                OPERATOR_DIVIDE -> if (secondOperandUtils.getValue() == ZERO_NUMBER_DOUBLE_TYPE) {
                    resultOperation = EMPTY_STRING
                    result = ResultUtils.ERROR_MESSAGE_DIVISION
                } else {
                    resultOperation =
                        (firstOperandUtils.getValue() / secondOperandUtils.getValue()).toString()
                    result = ResultUtils.SUCCESS
                }
                OPERATOR_MULTIPLY -> {
                    resultOperation =
                        (firstOperandUtils.getValue() * secondOperandUtils.getValue()).toString()
                    result = ResultUtils.SUCCESS
                }
                else -> {
                    ResultUtils.ERROR_MESSAGE.toString()
                    resultOperation = EMPTY_STRING
                }
            }
            updateFirstOperand()
            secondOperandUtils.eraseOperands()
        }
    }

    override fun getOperationResult(): String = resultOperation

    private fun updateFirstOperand() {
        if (resultOperation.substring(POSITION_ZERO).equals(OPERATOR_MINUS)) {
            firstOperandUtils.signs = OPERATOR_MINUS
            firstOperandUtils.value = resultOperation.substring(POSITION_ONE, resultOperation.length)
        } else {
            firstOperandUtils.signs = EMPTY_STRING
            firstOperandUtils.value = resultOperation
        }
        operator = EMPTY_STRING
    }

    override fun eraseResult(): String {
        resultOperation = EMPTY_STRING
        firstOperandUtils.eraseOperands()
        secondOperandUtils.eraseOperands()
        operator = EMPTY_STRING
        return EMPTY_STRING
    }

    override fun saveOperationSymbol(operatorSymbol: String) = when {
        firstOperandUtils.isEmpty() -> {
            firstOperandUtils.signs = OPERATOR_MINUS
        }
        operator.isEmpty() -> {
            operator = operatorSymbol
        }
        else -> secondOperandUtils.signs = OPERATOR_MINUS
    }

    override fun getResult() = result
}
