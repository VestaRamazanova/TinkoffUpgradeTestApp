package Kaspresso_myupgradeapplication

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.myupgradeapplication.MainActivity
import Kaspresso_myupgradeapplication.pages.KaspressoFirstScreen
import Kaspresso_myupgradeapplication.pages.KaspressoLoginScreen
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.kaspersky.components.alluresupport.interceptors.step.AllureMapperStepInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.views.DumpViewsInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams
import ru.tinkoff.myupgradeapplication.R

val kaspressoBuilder = Kaspresso.Builder.simple(
    customize = {
        flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 10_000, intervalMs = 100)
        beforeEachTest {
            // code before each test
            testLogger.i("Before")
        }
        afterEachTest {
            // code before each test
            testLogger.i("After")
        }
    }
).apply {
    testRunWatcherInterceptors.addAll(listOf(DumpViewsInterceptor(viewHierarchyDumper)))
    stepWatcherInterceptors.addAll(listOf(AllureMapperStepInterceptor()))
}

class KaspressoUserFlowTest : TestCase(kaspressoBuilder) {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun passwordEmptyTest() {
        run {

            KaspressoFirstScreen {
                nextButton.click()
            }

            KaspressoLoginScreen {
                loginField.typeText(loginValue)
                buttonSubmit.click()
                snackbar.text.hasText("Password field must be filled!")
            }
        }
    } //Выводится сообщение “Password field must be filled!”

    @Test
    fun loginEmptyTest() {
        run {

            KaspressoFirstScreen {
                nextButton.click()
            }

            KaspressoLoginScreen {
                passwordField.typeText(passwordValue)
                buttonSubmit.click()
                snackbar.text.hasText("Login field must be filled!")
            }
        }
    } //Выводится сообщение “Login field must be filled!”

    @Test
    fun loginPasswordEmptyTest() {
        run {

            KaspressoFirstScreen {
                nextButton.click()
            }

            KaspressoLoginScreen {
                buttonSubmit.click()
                snackbar.text.hasText("Both of fields must be filled!")
            }
        }
    } //Выводится сообщение “Both of fields must be filled!”

    @Test
    fun showDialogBoxTest() {

        KaspressoFirstScreen {
            showDialogButton.click()
            dialog.title.hasText("Важное сообщение")
            dialog.message.hasText("Теперь ты автоматизатор")

        }
    } //Выводится диалоговое окно с заголовком “Важное сообщение” и текстом “Теперь ты автоматизатор”.

    @Test
    fun closeDialogBoxTest() {

        KaspressoFirstScreen {
            showDialogButton.click()
            device.uiDevice.pressBack()
            nextButton.click()
        }
    } //Диалоговое окно больше не отображается на экране ( = можем нажать на кнопку Next)

    @Test
    fun returnTextTest() {
        KaspressoFirstScreen {
            textOnScreen.hasText(R.string.first_text)
            changeButton.click()
            textOnScreen.hasText(R.string.second_text)
            nextButton.click()
        }

        KaspressoLoginScreen().buttonPrevious.click()
        KaspressoFirstScreen().textOnScreen.hasText(R.string.first_text)
    } //В текстовом поле отображается текст по умолчанию (тот, который показывался при открытии приложения).

    @Test
    fun cleaningLoginPassword() {

        KaspressoFirstScreen().nextButton.click()

        KaspressoLoginScreen() {
            loginField.typeText(loginValue)
            passwordField.typeText(passwordValue)
            buttonPrevious.click()
        }
        KaspressoFirstScreen().nextButton.click()

        KaspressoLoginScreen() {
            editTextLogin.hasText(emptyField)
            editTextPassword.hasText(emptyField)
        }
    } //Поля login и password - пустые

    @Test
    fun enterLoginPasswordTest() {
        before {
            testLogger.i("action before test in test context")
        }.after {
            testLogger.i("action after test in test context")
        }.run {

            KaspressoFirstScreen {
                nextButton.click()
            }

            KaspressoLoginScreen {
                loginField.typeText(loginValue)
                passwordField.typeText(passwordValue)
                buttonSubmit.click()
                snackbar.text.hasText("You enter login = $loginValue password = $passwordValue")
            }
        }
    }

    @Test
    fun kaspressoTestFlakyAllure() {
        run {
            step("Navigate to Login Screen") {
                KaspressoFirstScreen {
                    nextButton.click()
                }
            }

            step("Check notification") {
                KaspressoLoginScreen {
                    step("Enter login, password") {
                        loginField.typeText(loginValue)
                        passwordField.typeText(passwordValue)
                    }
                    step("Click Confirm") {
                        buttonSubmit.click()
                    }
                    step("Check notification text") {
                        snackbar.text.hasText("You enter login = $loginValue password = $passwordValue")
                    }
                }
            }
        }
    }

    private companion object {
        const val loginValue = "Tinkoff"
        const val passwordValue = "Upgrade"
        const val emptyField = ""
    }
}