package ru.tinkoff.myupgradeapplication.pages

import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import androidx.test.uiautomator.UiDevice

class FirstPage : BasePage() {

    val nextButtonSelector = By.text("Next")
    val ChangeButtonSelector = By.text("Change")
    val ShowDialogButtonSelector = By.res("ru.tinkoff.myupgradeapplication:id/dialog_button")

    fun pressNextButton() {
        device
            .wait(Until.findObject(nextButtonSelector), waitingTimeOut)
            .click()
    }

    fun checkTextOnScreen(firstText: String) {
        assert(device.wait(Until.hasObject(By.text(firstText)), waitingTimeOut))
    }

    fun pressChangeButton() {
        device
           .wait(Until.findObject(ChangeButtonSelector), waitingTimeOut)
           .click()
    }

    fun pressShowDialogButton() {
        device
            .wait(Until.findObject(ShowDialogButtonSelector), waitingTimeOut)
            .click()
    }

    fun checkTitleOnDialogBox(text: String) {
        assert(device.wait(Until.hasObject(By.text(text)), waitingTimeOut))
    }

    fun checkTextOnDialogBox(text: String) {
        assert(device.wait(Until.hasObject(By.text(text)), waitingTimeOut))
    }
}