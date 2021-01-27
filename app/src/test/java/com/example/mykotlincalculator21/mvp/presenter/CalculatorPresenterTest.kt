package com.example.mykotlincalculator21.mvp.presenter

import com.example.mykotlincalculator21.mvp.contract.CalculatorContract
import com.example.mykotlincalculator21.mvp.model.CalculatorModel
import com.example.mykotlincalculator21.mvp.view.CalculatorView
import com.example.mykotlincalculator21.utils.ErrorUtils
import com.example.mykotlincalculator21.utils.StringUtils.EMPTY_STRING
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_DIVIDE
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_MINUS
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_MULTIPLY
import com.example.mykotlincalculator21.utils.StringUtils.OPERATOR_SUM
import com.example.mykotlincalculator21.utils.StringUtilsTest.NUMBER_FIVE
import com.example.mykotlincalculator21.utils.StringUtilsTest.NUMBER_FOUR
import com.example.mykotlincalculator21.utils.StringUtilsTest.NUMBER_THREE
import com.example.mykotlincalculator21.utils.StringUtilsTest.NUMBER_TWO
import com.example.mykotlincalculator21.utils.StringUtilsTest.NUMBER_ZERO
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorPresenterTest {
    private lateinit var presenter: CalculatorContract.CalculatorPresenterContract
    private lateinit var model: CalculatorContract.CalculatorModelContract
    private var view: CalculatorView = mock()

    @Before
    fun setup() {
        model = CalculatorModel()
        presenter = CalculatorPresenter(model, view)
    }

    @Test
    fun eraseTest() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_THREE)
        presenter.erase()
        assertEquals(EMPTY_STRING, model.getResult())
        verify(view).drawNumber(EMPTY_STRING)
    }

    @Test
    fun testOperationSymbolPressedPlus() {
        model.saveNumber(NUMBER_THREE)
        presenter.operationSymbolPressed(OPERATOR_SUM)
        verify(view).drawNumber(NUMBER_THREE + OPERATOR_SUM)
        assertEquals(NUMBER_THREE + OPERATOR_SUM, model.getValue())
    }

    @Test
    fun testOperationSymbolPressedNegative() {
        model.saveNumber(NUMBER_THREE)
        presenter.operationSymbolPressed(OPERATOR_MINUS)
        verify(view).drawNumber(NUMBER_THREE + OPERATOR_MINUS)
        assertEquals(NUMBER_THREE + OPERATOR_MINUS, model.getValue())
    }

    @Test
    fun testOperationSymbolPressedDivide() {
        model.saveNumber(NUMBER_THREE)
        presenter.operationSymbolPressed(OPERATOR_DIVIDE)
        verify(view).drawNumber(model.getValue())
        assertEquals(NUMBER_THREE + OPERATOR_DIVIDE, model.getValue())
    }

    @Test
    fun testOperationSymbolPressedWithMultiply() {
        model.saveNumber(NUMBER_THREE)
        presenter.operationSymbolPressed(OPERATOR_MULTIPLY)
        verify(view).drawNumber(model.getValue())
        assertEquals(NUMBER_THREE + OPERATOR_MULTIPLY, model.getValue())
    }


    @Test
    fun testPartialDivideOperation() {
        model.saveNumber(NUMBER_FOUR)
        model.saveOperationSymbol(OPERATOR_DIVIDE)
        presenter.numberPressed(NUMBER_TWO)
        verify(view).drawNumber(model.getValue())
        assertEquals(NUMBER_FOUR + OPERATOR_DIVIDE + NUMBER_TWO, model.getValue())
    }

    @Test
    fun testPartialMultiplyOperation() {
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_MULTIPLY)
        presenter.numberPressed(NUMBER_TWO)
        verify(view).drawNumber(model.getValue())
        assertEquals(NUMBER_TWO + OPERATOR_MULTIPLY + NUMBER_TWO, model.getValue())
    }

    @Test
    fun testFirstOperandPressed() {
        presenter.numberPressed(NUMBER_THREE)
        verify(view).drawNumber(model.getValue())
        assertEquals(NUMBER_THREE, model.getValue())
    }

    @Test
    fun testSecondOperandPressed() {
        model.saveNumber(NUMBER_THREE)
        model.saveOperationSymbol(OPERATOR_SUM)
        presenter.numberPressed(NUMBER_THREE)
        verify(view).drawNumber(model.getValue())
        assertEquals(NUMBER_THREE + OPERATOR_SUM + NUMBER_THREE, model.getValue())
    }

    @Test
    fun testSecondOperandNegativePressed() {
        model.saveNumber(NUMBER_THREE)
        model.saveOperationSymbol(OPERATOR_SUM)
        model.saveOperationSymbol(OPERATOR_MINUS)
        presenter.numberPressed(NUMBER_THREE)
        verify(view).drawNumber(model.getValue())
        assertEquals(NUMBER_THREE + OPERATOR_SUM + OPERATOR_MINUS + NUMBER_THREE, model.getValue())
    }

    @Test
    fun testFirstOperandNegativePressed() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_THREE)
        model.saveOperationSymbol(OPERATOR_SUM)
        presenter.numberPressed(NUMBER_THREE)
        verify(view).drawNumber(model.getValue())
        assertEquals(OPERATOR_MINUS + NUMBER_THREE + OPERATOR_SUM + NUMBER_THREE, model.getValue())
    }

    @Test
    fun testBothNegativeOperandsPressed() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_THREE)
        model.saveOperationSymbol(OPERATOR_SUM)
        model.saveOperationSymbol(OPERATOR_MINUS)
        presenter.numberPressed(NUMBER_THREE)
        verify(view).drawNumber(model.getValue())
        assertEquals(OPERATOR_MINUS + NUMBER_THREE + OPERATOR_SUM + OPERATOR_MINUS + NUMBER_THREE, model.getValue())
    }

    @Test
    fun testOperationSum() {
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_SUM)
        model.saveNumber(NUMBER_THREE)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(NUMBER_FIVE, model.getResult())
    }

    @Test
    fun testOperationSumNegativeSecondOperand() {
        model.saveNumber(NUMBER_FOUR)
        model.saveOperationSymbol(OPERATOR_SUM)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(NUMBER_TWO, model.getResult())
    }

    @Test
    fun testOperationSumNegativeFirstOperand() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_FOUR)
        model.saveOperationSymbol(OPERATOR_SUM)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(OPERATOR_MINUS + NUMBER_TWO, model.getResult())
    }

    @Test
    fun testOperationSumBothOperandNegative() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_SUM)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(OPERATOR_MINUS + NUMBER_FOUR, model.getResult())
    }

    @Test
    fun testOperationMinusFirstOperandNegative() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(OPERATOR_MINUS + NUMBER_FOUR, model.getResult())
    }

    @Test
    fun testOperationMinusSecondOperandNegative() {
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(NUMBER_FOUR, model.getResult())
    }

    @Test
    fun testOperationMinus() {
        model.saveNumber(NUMBER_FOUR)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(NUMBER_TWO, model.getResult())
    }

    @Test
    fun testOperationMinusBothOperandsNegative() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(NUMBER_ZERO, model.getResult())
    }

    @Test
    fun testOperationDivide() {
        model.saveNumber(NUMBER_FOUR)
        model.saveOperationSymbol(OPERATOR_DIVIDE)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(NUMBER_TWO, model.getResult())
    }

    @Test
    fun testOperationDivideBothOperandsNegative() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_FOUR)
        model.saveOperationSymbol(OPERATOR_DIVIDE)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(NUMBER_TWO, model.getResult())
    }

    @Test
    fun testOperationDivideFirstOperandNegative() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_FOUR)
        model.saveOperationSymbol(OPERATOR_DIVIDE)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(OPERATOR_MINUS + NUMBER_TWO, model.getResult())
    }

    @Test
    fun testOperationDivideSecondOperandNegative() {
        model.saveNumber(NUMBER_FOUR)
        model.saveOperationSymbol(OPERATOR_DIVIDE)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(OPERATOR_MINUS + NUMBER_TWO, model.getResult())
    }

    @Test
    fun testOperationDivideByZero() {
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_DIVIDE)
        model.saveNumber(NUMBER_ZERO)
        presenter.operatorResultPressed()
        verify(view).showErrorDivision()
        assertEquals(ErrorUtils.ERROR_MESSAGE_DIVISION, model.getError())
    }

    @Test
    fun testOperationMultiply() {
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_MULTIPLY)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(NUMBER_FOUR, model.getResult())
    }

    @Test
    fun testOperationMultiplySecondOperandNegative() {
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_MULTIPLY)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(OPERATOR_MINUS + NUMBER_FOUR, model.getResult())
    }

    @Test
    fun testOperationMultiplyFirstOperandNegative() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_MULTIPLY)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(OPERATOR_MINUS + NUMBER_FOUR, model.getResult())
    }

    @Test
    fun testOperationMultiplyBothOperandsNegative() {
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        model.saveOperationSymbol(OPERATOR_MULTIPLY)
        model.saveOperationSymbol(OPERATOR_MINUS)
        model.saveNumber(NUMBER_TWO)
        presenter.operatorResultPressed()
        verify(view).drawNumber(model.getResult())
        assertEquals(NUMBER_FOUR, model.getResult())
    }

}
