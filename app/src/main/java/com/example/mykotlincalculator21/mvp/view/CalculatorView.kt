package com.example.mykotlincalculator21.mvp.view

import com.example.mykotlincalculator21.MainActivity
import com.example.mykotlincalculator21.R
import com.example.mykotlincalculator21.databinding.ActivityMainBinding
import com.example.mykotlincalculator21.mvp.contract.CalculatorContract

class CalculatorView(mainActivity: MainActivity, private val binding: ActivityMainBinding) :
    ActivityView(mainActivity), CalculatorContract.CalculatorViewContract {

    override fun drawNumber(number: String) {
        binding.screenTextExample.setText(number)
    }

    override fun showErrorDivision() {
        binding.screenTextExample.setText(R.string.error_message_in_division)

    }

    override fun showErrorInvalidOperation() {
        binding.screenTextExample.setText(R.string.error_message_when_invalid_format)
    }

    override fun showErrorMessage() {
        binding.screenTextExample.setText(R.string.error_generic_message)
    }

}
