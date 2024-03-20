package ru.tinkoff.myupgradeapplication

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import androidx.test.uiautomator.By
import org.junit.Rule
import org.junit.Test
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import ru.tinkoff.myupgradeapplication.pages.FirstPage
import ru.tinkoff.myupgradeapplication.pages.LoginPage
import ru.tinkoff.myupgradeapplication.MainActivity

@RunWith(AndroidJUnit4::class)
class UserFlowTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun emptyPasswordFieldTest() {
        val loginValue = "Tinkoff"

        with(FirstPage()) {
            pressNextButton()
        }

        with(LoginPage()) {
            enterLogin(loginValue)
            pressSubmitButton()
            checkTextOnSnackBar("Password field must be filled!")
        }
    } //Выводится сообщение “Password field must be filled!”

    @Test
    fun emptyLoginFieldTest() {
        val passwordValue = "Upgrade"

        with(FirstPage()) {
            pressNextButton()
        }

        with(LoginPage()) {
            enterPassword(passwordValue)
            pressSubmitButton()
            checkTextOnSnackBar("Login field must be filled!")
        }
    } //Выводится сообщение “Login field must be filled!”

    @Test
    fun emptyLoginPasswordFieldTest() {
        val loginValue = ""
        val passwordValue = ""

        with(FirstPage()) {
            pressNextButton()
        }

        with(LoginPage()) {
            enterLogin(loginValue)
            enterPassword(passwordValue)
            pressSubmitButton()
            checkTextOnSnackBar("Both of fields must be filled!")
        }
    } //Выводится сообщение “Both of fields must be filled!”

    @Test
    fun showDialogBoxTest() {

        with(FirstPage()) {
            pressShowDialogButton()
            checkTitleOnDialogBox("Важное сообщение")
            checkTextOnDialogBox("Теперь ты автоматизатор")
        }
    } //Выводится диалоговое окно с заголовком “Важное сообщение” и текстом “Теперь ты автоматизатор”.

    @Test
    fun closeDialogBoxTest() {

        with(FirstPage()) {
            pressShowDialogButton()
            Thread.sleep(500)
            device.pressBack()
            pressNextButton()
        }
    } //Диалоговое окно больше не отображается на экране ( = можем нажать на кнопку Next)

    @Test
    fun checkReturnTextTest() {
        val firstText = getInstrumentation().targetContext.getString(R.string.first_text);
        val secondText = getInstrumentation().targetContext.getString(R.string.second_text)

        with(FirstPage()) {
            checkTextOnScreen(firstText)
            pressChangeButton()
            checkTextOnScreen(secondText)
            pressNextButton()
        }
        with(LoginPage()) {
            pressPreviousButton()
        }
        with(FirstPage()) {
            checkTextOnScreen(firstText)
        }
    } //В текстовом поле отображается текст по умолчанию (тот, который показывался при открытии приложения).

    @Test
    fun cleaningLoginPasswordTest() {
        val loginValue = "Tinkoff"
        val passwordValue = "Upgrade"
        val emptyField = ""

        with(FirstPage()) {
            pressNextButton()
        }
        with(LoginPage()) {
            enterLogin(loginValue)
            enterPassword(passwordValue)
            pressPreviousButton()
        }
        with(FirstPage()) {
            pressNextButton()
        }
        with(LoginPage()) {
            checkTextInLoginField(emptyField)
            checkTextInPasswordField(emptyField)
        }
    } //Поля login и password - пустые

    @Test
    fun enterLoginPasswordTest() {
        val loginValue = "Tinkoff"
        val passwordValue = "Upgrade"

        with(FirstPage()) {
            pressNextButton()
        }

        with(LoginPage()) {
            enterLogin(loginValue)
            enterPassword(passwordValue)
            pressSubmitButton()
            checkTextOnSnackBar("You enter login = $loginValue password = $passwordValue")
        }
    } //Текст на снэкбаре показывает, какой логин и пароль были введены

    @Test
    fun checkSwitchingTextTest() {
        val firstText = getInstrumentation().targetContext.getString(R.string.first_text);
        val secondText = getInstrumentation().targetContext.getString(R.string.second_text)

        with(FirstPage()) {
            checkTextOnScreen(firstText)
            pressChangeButton()
            checkTextOnScreen(secondText)
        }
    } //После нажатия на кнопку Change текст меняется
}