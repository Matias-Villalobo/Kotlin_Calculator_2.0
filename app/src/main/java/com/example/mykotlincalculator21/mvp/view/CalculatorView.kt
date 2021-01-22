package com.example.mykotlincalculator21.mvp.view

import com.example.mykotlincalculator21.MainActivity
import com.example.mykotlincalculator21.R
import com.example.mykotlincalculator21.databinding.ActivityMainBinding
import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import java.security.AccessController.getContext

class CalculatorView(mainActivity: MainActivity, private val binding: ActivityMainBinding) :
    ActivityView(mainActivity), CalculatorContract.CalculatorViewContract {

    override fun drawNumber(number: String) {
        binding.screenTextExample.setText(number)
    }

    override fun showErrorDivision() {
        val context = getContext()
        if (context != null) {
            binding.screenTextExample.setText(R.string.error_message_in_division)
        }
    }

    override fun showErrorInvalidOperation() {
        val context = getContext()
        if (context != null) {
            binding.screenTextExample.setText(R.string.error_message_when_invalid_format)
        }
    }

    override fun showErrorMessage() {
        val context = getContext()
        if (context != null) {
            binding.screenTextExample.setText(R.string.error_generic_message)
        }
    }

}
