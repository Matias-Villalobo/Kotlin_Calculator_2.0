package com.example.mykotlincalculator21.mvp.presenter

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import com.example.mykotlincalculator21.utils.ErrorUtils

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
        model.doOperations()
        when (model.getError()) {
            ErrorUtils.NONE -> {
                view.drawNumber(model.getResult())
            }
            ErrorUtils.ERROR_MESSAGE -> {
                view.showErrorMessage()
            }
            ErrorUtils.ERROR_MESSAGE_DIVISION -> {
                view.showErrorDivision()
            }
            ErrorUtils.ERROR_MESSAGE_INVALID_FORMAT -> {
                view.showErrorInvalidOperation()
            }
        }
    }

    override fun erase() {
        view.drawNumber(model.eraseResult())
    }
}
