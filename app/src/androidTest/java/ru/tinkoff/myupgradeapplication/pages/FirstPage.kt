package ru.tinkoff.myupgradeapplication.pages

import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import androidx.test.uiautomator.UiDevice

class FirstPage : BasePage() {

    val nextButtonSelector = By.text(NEXT_BUTTON_TEXT)
    val changeButtonSelector = By.text(CHANGE_BUTTON_TEXT)
    val showDialogButtonSelector = By.res(SHOW_DIALOG_BUTTON_RES)

    fun pressNextButton() {
        clickElementBySelector(nextButtonSelector)
    }

    fun checkTextOnScreen(firstText: String) {
        assert(device.wait(Until.hasObject(By.text(firstText)), waitingTimeOut))
    }

    fun pressChangeButton() {
        clickElementBySelector(changeButtonSelector)
    }

    fun pressShowDialogButton() {
        clickElementBySelector(showDialogButtonSelector)
    }

    fun checkTitleOnDialogBox(text: String) {
        assert(device.wait(Until.hasObject(By.text(text)), waitingTimeOut))
    }

    fun checkTextOnDialogBox(text: String) {
        assert(device.wait(Until.hasObject(By.text(text)), waitingTimeOut))
    }

    private companion object {
        const val NEXT_BUTTON_TEXT = "Next"
        const val CHANGE_BUTTON_TEXT = "Change"
        const val SHOW_DIALOG_BUTTON_RES = "ru.tinkoff.myupgradeapplication:id/dialog_button"
    }
}