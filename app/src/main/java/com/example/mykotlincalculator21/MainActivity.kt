package com.example.mykotlincalculator21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mykotlincalculator21.databinding.ActivityMainBinding
import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import com.example.mykotlincalculator21.mvp.model.CalculatorModel
import com.example.mykotlincalculator21.mvp.presenter.CalculatorPresenter
import com.example.mykotlincalculator21.mvp.view.CalculatorView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: CalculatorContract.CalculatorPresenterContract
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CalculatorPresenter(CalculatorModel(), CalculatorView(this, binding))
        setListeners()
    }

    private fun setListeners() {
        numberPressed(binding.buttonNumberZero)
        numberPressed(binding.buttonNumberOne)
        numberPressed(binding.buttonNumberTwo)
        numberPressed(binding.buttonNumberThree)
        numberPressed(binding.buttonNumberFour)
        numberPressed(binding.buttonNumberFive)
        numberPressed(binding.buttonNumberSix)
        numberPressed(binding.buttonNumberSeven)
        numberPressed(binding.buttonNumberEight)
        numberPressed(binding.buttonNumberNine)
        numberPressed(binding.buttonFunctionErase)
    }

    private fun numberPressed(button: Button) {
        button.setOnClickListener { presenter.numberPressed(button.text.toString()) }
    }

}
