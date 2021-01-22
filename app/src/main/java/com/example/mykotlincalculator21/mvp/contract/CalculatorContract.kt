package com.example.mykotlincalculator21.mvp.contract

import com.example.mykotlincalculator21.utils.ErrorUtils

interface CalculatorContract {
    interface CalculatorPresenterContract {
        fun numberPressed(number: String)

        fun operationSymbolPressed(operatorUsed: String)

        fun operatorResultPressed()

        fun erase()
    }

    interface CalculatorModelContract {
        fun saveNumber(number: String)

        fun getPartialResult(): String

        fun getResult(): String

        fun eraseResult(): String

        fun saveOperationSymbol(operatorSymbol: String)

        fun doOperations()

        fun getError(): ErrorUtils

    }

    interface CalculatorViewContract {
        fun drawNumber(number: String)

        fun showErrorDivision()

        fun showErrorInvalidOperation()

        fun showErrorMessage()

    }
}
