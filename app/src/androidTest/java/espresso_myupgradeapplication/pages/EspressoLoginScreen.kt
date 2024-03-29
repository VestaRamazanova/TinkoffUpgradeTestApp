package espresso_myupgradeapplication.pages

import androidx.appcompat.widget.AppCompatTextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.SnackbarContentLayout
import com.google.android.material.textview.MaterialTextView
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import ru.tinkoff.myupgradeapplication.R

class EspressoLoginScreen {
    val buttonPreviousMatcher = withId(R.id.button_second)
    val buttonSubmitMatcher = withId(R.id.button_submit)
    val editTextLoginFieldMatcher = withId(R.id.edittext_login)
    val editTextPasswordFieldMatcher = withId(R.id.edittext_password)
    val snackBarTextViewMatchers = allOf(
        ViewMatchers.withParent(Matchers.instanceOf(SnackbarContentLayout::class.java)),
        Matchers.instanceOf(MaterialTextView::class.java)
    )

    fun clickPreviousButton() {
        onView(buttonPreviousMatcher)
            .perform(click())
    }

    fun clickSubmitButton() {
        onView(buttonSubmitMatcher)
            .perform(click())
    }

    fun typeTextToLoginField(text: String) {
        onView(editTextLoginFieldMatcher)
            .perform(typeText(text))
    }

    fun typeTextToPasswordField(text: String) {
        onView(editTextPasswordFieldMatcher)
            .perform(typeText(text))
    }

    fun checkTextOnSnackBar(text: String) {
        onView(snackBarTextViewMatchers)
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    fun checkTextInLoginField(text: String) {
        onView(editTextLoginFieldMatcher)
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    fun checkTextInPasswordField(text: String) {
        onView(editTextPasswordFieldMatcher)
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }
}