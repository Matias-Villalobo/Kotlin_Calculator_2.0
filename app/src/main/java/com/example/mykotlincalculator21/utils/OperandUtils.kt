package com.example.mykotlincalculator21.utils

import com.example.mykotlincalculator21.utils.NumbersUtils.ZERO_NUMBER_DOUBLE_TYPE
import com.example.mykotlincalculator21.utils.StringUtils.EMPTY_STRING

class OperandUtils {
    var signs = EMPTY_STRING
    var value = EMPTY_STRING

    fun eraseOperands() {
        signs = EMPTY_STRING
        value = EMPTY_STRING
    }

    fun getValue(): Double {
        if (value === EMPTY_STRING) {
            return ZERO_NUMBER_DOUBLE_TYPE
        } else {
            return (signs + value).toDouble()
        }
    }

    fun setSign(sign: String) {
        this.signs = sign
    }

    fun isEmpty(): Boolean {
        return value.isEmpty()
    }

    fun addNumber(number: String) {
        value += number
    }
}
