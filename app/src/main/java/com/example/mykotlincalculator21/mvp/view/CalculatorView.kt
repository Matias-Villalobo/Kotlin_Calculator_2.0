package com.example.mykotlincalculator21.mvp.view

import com.example.mykotlincalculator21.MainActivity
import com.example.mykotlincalculator21.databinding.ActivityMainBinding
import com.example.mykotlincalculator21.mvp.contract.CalculatorContract

class CalculatorView(mainActivity: MainActivity, binding: ActivityMainBinding): ActivityView(mainActivity), CalculatorContract.CalculatorViewContract {

    private var binding:ActivityMainBinding

    init{
        this.binding = binding
    }
    override fun drawNumber(number: String) {
        binding.screenTextExample.setText(number)
    }

}
