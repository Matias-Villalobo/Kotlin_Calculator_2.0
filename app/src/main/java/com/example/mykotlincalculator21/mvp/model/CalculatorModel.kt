package com.example.mykotlincalculator21.mvp.model

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import com.example.mykotlincalculator21.utils.EMPTY_STRING

class CalculatorModel : CalculatorContract.CalculatorModelContract {
    private var firstOperand: String = EMPTY_STRING

    override fun saveNumber(number: String) {
        firstOperand = number
    }

    override fun getResult(): String? {
        return firstOperand
    }
}
