package com.example.mykotlincalculator21.mvp.contract

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

        fun getFullResult(): String

        fun eraseResult(): String

        fun saveOperationSymbol(operatorSymbol: String)

    }

    interface CalculatorViewContract {
        fun drawNumber(number: String)
    }
}
