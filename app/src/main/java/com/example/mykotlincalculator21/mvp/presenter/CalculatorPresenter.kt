package com.example.mykotlincalculator21.mvp.presenter

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import com.example.mykotlincalculator21.utils.ResultUtils

class CalculatorPresenter(
    private val model: CalculatorContract.CalculatorModelContract,
    private val view: CalculatorContract.CalculatorViewContract
) : CalculatorContract.CalculatorPresenterContract {

    override fun numberPressed(number: String) {
        model.saveNumber(number)
        view.drawNumber(model.getValue())
    }

    override fun operationSymbolPressed(operatorUsed: String) {
        model.saveOperationSymbol(operatorUsed)
        view.drawNumber(model.getValue())
    }

    override fun operatorResultPressed() {
        model.doOperations()
        when (model.getResult()) {
            ResultUtils.SUCCESS -> {
                view.drawNumber(model.getOperationResult())
            }
            ResultUtils.ERROR_MESSAGE -> {
                view.showErrorMessage()
            }
            ResultUtils.ERROR_MESSAGE_DIVISION -> {
                view.showErrorDivision()
            }
            ResultUtils.ERROR_MESSAGE_INVALID_FORMAT -> {
                view.showErrorInvalidOperation()
            }
        }
    }

    override fun erase() {
        view.drawNumber(model.eraseResult())
    }
}
