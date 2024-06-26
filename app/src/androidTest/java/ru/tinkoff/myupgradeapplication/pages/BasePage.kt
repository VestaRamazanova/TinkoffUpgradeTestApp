package ru.tinkoff.myupgradeapplication.pages

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

open class BasePage() {

    val device = UiDevice.getInstance(getInstrumentation())
    val waitingTimeOut = 3000L

    fun clickElementBySelector(selector: BySelector) {
        device
            .wait(Until.findObject(selector), waitingTimeOut)
            .click()
    }
}
