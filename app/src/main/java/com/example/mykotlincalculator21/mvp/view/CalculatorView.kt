package com.example.mykotlincalculator21.mvp.view

import com.example.mykotlincalculator21.MainActivity
import com.example.mykotlincalculator21.databinding.ActivityMainBinding
import com.example.mykotlincalculator21.mvp.contract.CalculatorContract

class CalculatorView(mainActivity: MainActivity, private val binding: ActivityMainBinding) :
    ActivityView(mainActivity), CalculatorContract.CalculatorViewContract {

    override fun drawNumber(number: String) {
        binding.screenTextExample.setText(number)
    }

}
