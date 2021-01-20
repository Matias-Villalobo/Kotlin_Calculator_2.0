package com.example.mykotlincalculator21.mvp.presenter

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract

class CalculatorPresenter(model: CalculatorContract.CalculatorModelContract, view: CalculatorContract.CalculatorViewContract): CalculatorContract.CalculatorPresenterContract {

    private val model: CalculatorContract.CalculatorModelContract
    private val view: CalculatorContract.CalculatorViewContract

    init{
        this.model = model
        this.view = view
    }

    override fun numberPressed(number:String) {
        model.saveNumber(number)
        view.drawNumber(model.firstOperand)
    }
}
