import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import ru.tinkoff.myupgradeapplication.MainActivity
import ru.tinkoff.myupgradeapplication.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.SnackbarContentLayout
import com.google.android.material.textview.MaterialTextView
import espresso_myupgradeapplication.matchers.TextViewHintColorMatcher
import espresso_myupgradeapplication.pages.EspressoFirstScreen
import espresso_myupgradeapplication.pages.EspressoLoginScreen
import org.hamcrest.Matchers.*
import ru.tinkoff.myupgradeapplication.pages.FirstPage
import ru.tinkoff.myupgradeapplication.pages.LoginPage

@RunWith(AndroidJUnit4::class)
class EspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkPasswordEmpty() {

        EspressoFirstScreen().clickNextButton()

        with(EspressoLoginScreen()) {
            typeTextToLoginField(loginValue)
            clickSubmitButton()
            checkTextOnSnackBar("Password field must be filled!")
        }
    } //Выводится сообщение “Password field must be filled!”

    @Test
    fun checkLoginEmpty() {

        EspressoFirstScreen().clickNextButton()

        with(EspressoLoginScreen()) {
            typeTextToPasswordField(passwordValue)
            clickSubmitButton()
            checkTextOnSnackBar("Login field must be filled!")
        }
    } //Выводится сообщение “Login field must be filled!”

    @Test
    fun checkLoginPasswordEmpty() {

        EspressoFirstScreen().clickNextButton()

        with(EspressoLoginScreen()) {
            typeTextToLoginField(emptyField)
            typeTextToPasswordField(emptyField)
            clickSubmitButton()
            checkTextOnSnackBar("Both of fields must be filled!")
        }
    } //Выводится сообщение “Both of fields must be filled!”

    @Test
    fun showDialogBox() {

        with(EspressoFirstScreen()) {
            clickShowDialogButton()
            checkTitleOnDialogBox("Важное сообщение")
            checkTextOnDialogBox("Теперь ты автоматизатор")
        }
    } //Выводится диалоговое окно с заголовком “Важное сообщение” и текстом “Теперь ты автоматизатор”.

    @Test
    fun closeDialogBox() {

        with(EspressoFirstScreen()) {
            clickShowDialogButton()
            Espresso.pressBack()
            clickNextButton()
        }
    } //Диалоговое окно больше не отображается на экране ( = можем нажать на кнопку Next)

    @Test
    fun checkReturnText() {
        // Получение контекста приложения и строк из ресурсов (можно было использовать и через InstrumentationRegistry)
        val firstText = ApplicationProvider.getApplicationContext<Context>().getString(R.string.first_text)
        val secondText = ApplicationProvider.getApplicationContext<Context>().getString(R.string.second_text)

        with(EspressoFirstScreen()) {
            checkTextOnScreen(firstText)
            clickChangeButton()
            checkTextOnScreen(secondText)
            clickNextButton()
        }

        EspressoLoginScreen().clickPreviousButton()
        EspressoFirstScreen().checkTextOnScreen(firstText)
    } //В текстовом поле отображается текст по умолчанию (тот, который показывался при открытии приложения).

    @Test
    fun cleaningLoginPassword() {

        EspressoFirstScreen().clickNextButton()

        with(EspressoLoginScreen()) {
            typeTextToLoginField(loginValue)
            typeTextToPasswordField(passwordValue)
            clickPreviousButton()
        }
        EspressoFirstScreen().clickNextButton()

        with(EspressoLoginScreen()) {
            checkTextInLoginField(emptyField)
            checkTextInPasswordField(emptyField)
        }
    } //Поля login и password - пустые

    @Test
    fun fragmentNavigationTest() {

        EspressoFirstScreen().clickNextButton()
        EspressoLoginScreen().clickPreviousButton()
        EspressoFirstScreen().checkScreenTitle("First Fragment")
    } //Заголовок страницы - First Fragment

    @Test
    fun checkFabNotification() {
        with(EspressoFirstScreen()) {
            clickFab()
            checkTextOnSnackBar("Replace with your own action")
        }
    } //По тапу на конвертик выводится сообщение “Replace with your own action”

    @Test
    fun enterLoginPassword() {

        EspressoFirstScreen().clickNextButton()

        with(EspressoLoginScreen()) {
            typeTextToLoginField(loginValue)
            typeTextToPasswordField(passwordValue)
            clickSubmitButton()
            checkTextOnSnackBar("You enter login = $loginValue password = $passwordValue")
        }
    } //Текст на снэкбаре показывает, какой логин и пароль были введены

    @Test
    fun chekHintColor() {
        EspressoFirstScreen().clickNextButton()
        EspressoLoginScreen().clickSubmitButton()

        onView(withId(R.id.edittext_login))
            .check(matches(TextViewHintColorMatcher(R.color.error_hint)))
    }//Цвет хинта в поле логина - красный, если не заполнить логин

    val loginValue = "Tinkoff"
    val passwordValue = "Upgrade"
    val emptyField = ""
}