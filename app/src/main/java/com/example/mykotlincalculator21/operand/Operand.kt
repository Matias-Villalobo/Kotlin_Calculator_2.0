package com.example.mykotlincalculator21.operand

import com.example.mykotlincalculator21.utils.NumbersUtils.ZERO_NUMBER_DOUBLE_TYPE
import com.example.mykotlincalculator21.utils.StringUtils.EMPTY_STRING

class Operand {
    var signs: String = EMPTY_STRING
    var value: String = EMPTY_STRING

    fun eraseOperands() {
        signs = EMPTY_STRING
        value = EMPTY_STRING
    }

    fun getValue(): Double {
        return if (value == EMPTY_STRING) {
            ZERO_NUMBER_DOUBLE_TYPE
        } else {
            (signs + value).toDouble()
        }
    }

    fun isEmpty() = value.isEmpty()

    fun addNumber(number: String) {
        value += number
    }
}
