package ru.tinkoff.myupgradeapplication.pages

import androidx.test.uiautomator.Until
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.By


class LoginPage : BasePage() {

    val loginFieldSelector = By.res("ru.tinkoff.myupgradeapplication:id/edittext_login")
    val passwordFieldSelector = By.res("ru.tinkoff.myupgradeapplication:id/edittext_password")
    val submitButton = By.res("ru.tinkoff.myupgradeapplication:id/button_submit")
    val previousButton = By.res("ru.tinkoff.myupgradeapplication:id/button_second")
    fun enterLogin(loginValue: String) {
        device
            .wait(Until.findObject(loginFieldSelector), waitingTimeOut)
            .text = loginValue
    }

    fun enterPassword(passwordValue: String) {
        device
            .wait(Until.findObject(passwordFieldSelector), waitingTimeOut)
            .text = passwordValue
    }

    fun pressSubmitButton() {
        device
            .wait(Until.findObject(submitButton), waitingTimeOut)
            .click()
    }
    fun checkTextOnSnackBar(text: String) {
        assert(device.wait(Until.hasObject(By.text(text)), waitingTimeOut))
    }

    fun pressPreviousButton() {
        device
            .wait(Until.findObject(previousButton), waitingTimeOut)
            .click()
    }

    fun checkTextInLoginField(text: String) {
        assert(device.wait(Until.hasObject(By.text(text)), waitingTimeOut))
    }

    fun checkTextInPasswordField(text: String) {
        assert(device.wait(Until.hasObject(By.text(text)), waitingTimeOut))
    }
}