package com.example.mykotlincalculator21.mvp.model

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import com.example.mykotlincalculator21.utils.*

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

    override fun getPartialResult(): String {
        return (firstOperand + operator + secondOperand)
    }

    override fun getFullResult(): String {
        when (operator) {
            OPERATOR_SUM -> result =
                ((java.lang.Double.parseDouble(firstOperand) + java.lang.Double.parseDouble(
                    secondOperand
                ))).toString()
            OPERATOR_MINUS -> result =
                ((java.lang.Double.parseDouble(firstOperand) - java.lang.Double.parseDouble(
                    secondOperand
                ))).toString()
            OPERATOR_DIVIDE -> result =
                ((java.lang.Double.parseDouble(firstOperand) / java.lang.Double.parseDouble(
                    secondOperand
                ))).toString()
            OPERATOR_MULTIPLY -> result =
                ((java.lang.Double.parseDouble(firstOperand) * java.lang.Double.parseDouble(
                    secondOperand
                ))).toString()
            else -> result = ERROR_MESSAGE
        }
        return result
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
