package ru.tinkoff.myupgradeapplication.pages

import androidx.test.uiautomator.Until
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.By


class LoginPage : BasePage() {

    companion object {
        private const val LOGIN_FIELD_RES = "ru.tinkoff.myupgradeapplication:id/edittext_login"
        private const val PASSWORD_FIELD_RES = "ru.tinkoff.myupgradeapplication:id/edittext_password"
        private const val SUBMIT_BUTTON_RES = "ru.tinkoff.myupgradeapplication:id/button_submit"
        private const val PREVIOUS_BUTTON_RES = "ru.tinkoff.myupgradeapplication:id/button_second"
    }

    val loginFieldSelector = By.res(LOGIN_FIELD_RES)
    val passwordFieldSelector = By.res(PASSWORD_FIELD_RES)
    val submitButton = By.res(SUBMIT_BUTTON_RES)
    val previousButton = By.res(PREVIOUS_BUTTON_RES)

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
        clickElementBySelector(submitButton)
    }

    fun checkTextOnSnackBar(text: String) {
        assert(device.wait(Until.hasObject(By.text(text)), waitingTimeOut))
    }

    fun pressPreviousButton() {
        clickElementBySelector(previousButton)
    }

    fun checkTextInLoginField(text: String) {
        assert(device.wait(Until.hasObject(By.text(text)), waitingTimeOut))
    }

    fun checkTextInPasswordField(text: String) {
        assert(device.wait(Until.hasObject(By.text(text)), waitingTimeOut))
    }
}