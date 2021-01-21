package com.example.mykotlincalculator21.mvp.presenter

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract

class CalculatorPresenter(
    private val model: CalculatorContract.CalculatorModelContract,
    private val view: CalculatorContract.CalculatorViewContract
) : CalculatorContract.CalculatorPresenterContract {

    override fun numberPressed(number: String) {
        model.saveNumber(number)
        view.drawNumber(model.getPartialResult())
    }

    override fun operationSymbolPressed(operatorUsed: String) {
        model.saveOperationSymbol(operatorUsed)
        view.drawNumber(model.getPartialResult())
    }

    override fun operatorResultPressed() {
        view.drawNumber(model.getFullResult())
    }

    override fun erase() {
        view.drawNumber(model.eraseResult())
    }
}
