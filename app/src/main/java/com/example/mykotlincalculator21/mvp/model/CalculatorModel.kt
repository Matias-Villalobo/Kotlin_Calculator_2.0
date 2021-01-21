package com.example.mykotlincalculator21.mvp.model

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import com.example.mykotlincalculator21.utils.EMPTY_STRING
import com.example.mykotlincalculator21.utils.ERROR_MESSAGE
import com.example.mykotlincalculator21.utils.OPERATOR_DIVIDE
import com.example.mykotlincalculator21.utils.OPERATOR_MINUS
import com.example.mykotlincalculator21.utils.OPERATOR_MULTIPLY
import com.example.mykotlincalculator21.utils.OPERATOR_SUM

class CalculatorModel : CalculatorContract.CalculatorModelContract {
    private var firstOperand: String = EMPTY_STRING
    private var secondOperand: String = EMPTY_STRING
    private var operator: String = EMPTY_STRING
    private var result: String = EMPTY_STRING

    override fun saveNumber(number: String) {
        if (operator.isEmpty()) {
            firstOperand += number
        } else {
            secondOperand += number
        }
    }

    override fun getPartialResult() = "$firstOperand$operator$secondOperand"

    override fun getFullResult(): String {
        var firstOperandValue = firstOperand.toDouble()
        var secondOperandValue = secondOperand.toDouble()

        return when (operator) {
            OPERATOR_SUM -> (firstOperandValue + secondOperandValue).toString()
            OPERATOR_MINUS -> (firstOperandValue - secondOperandValue).toString()
            OPERATOR_DIVIDE -> (firstOperandValue / secondOperandValue).toString()
            OPERATOR_MULTIPLY -> (firstOperandValue * secondOperandValue).toString()
            else -> ERROR_MESSAGE
        }
    }

    override fun eraseResult(): String {
        result = EMPTY_STRING
        firstOperand = EMPTY_STRING
        secondOperand = EMPTY_STRING
        operator = EMPTY_STRING
        return EMPTY_STRING
    }

    override fun saveOperationSymbol(operatorSymbol: String) {
        operator = operatorSymbol
    }

}
