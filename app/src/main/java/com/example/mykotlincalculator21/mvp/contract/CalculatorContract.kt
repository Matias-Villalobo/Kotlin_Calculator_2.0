package com.example.mykotlincalculator21.mvp.contract

interface CalculatorContract {
    interface CalculatorPresenterContract {
        fun numberPressed(number: String)
    }

    interface CalculatorModelContract {
        fun saveNumber(number: String)
        var firstOperand: String
    }

    interface CalculatorViewContract {
        fun drawNumber(number: String)
    }
}
