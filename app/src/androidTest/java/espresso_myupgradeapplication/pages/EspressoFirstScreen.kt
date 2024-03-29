package espresso_myupgradeapplication.pages

import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.DialogTitle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.SnackbarContentLayout
import com.google.android.material.textview.MaterialTextView
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import ru.tinkoff.myupgradeapplication.R

class EspressoFirstScreen {
    val nextButtonMatcher = withId(R.id.button_first)
    val changeButtonMatcher = withId(R.id.change_button)
    val showDialogMatcher = withId(R.id.dialog_button)
    val snackBarTextViewMatchers = allOf(
        ViewMatchers.withParent(Matchers.instanceOf(SnackbarContentLayout::class.java)),
        Matchers.instanceOf(MaterialTextView::class.java))
    val fabMatcher = withId(R.id.fab)
    val screenTitleMatcher = allOf(
        ViewMatchers.withParent(Matchers.instanceOf(MaterialToolbar::class.java)),
        Matchers.instanceOf(AppCompatTextView::class.java))
    val titleOnDialogMatcher = allOf(
        ViewMatchers.withParent(Matchers.instanceOf(LinearLayout::class.java)),
        Matchers.instanceOf(DialogTitle::class.java))
    val textOnDialogViewMatcher = allOf(
        ViewMatchers.withParent(Matchers.instanceOf(LinearLayout::class.java)),
        Matchers.instanceOf(MaterialTextView::class.java))
    val textOnScreen = withId(R.id.textview_first)

    fun clickNextButton() {
        onView(nextButtonMatcher)
            .perform(click())
    }

    fun clickChangeButton() {
        onView(changeButtonMatcher)
            .perform(click())
    }

    fun clickShowDialogButton() {
        onView(showDialogMatcher)
            .perform(click())
    }

    fun clickFab() {
        onView(fabMatcher)
            .perform(click())
    }

    fun checkScreenTitle(title: String) {
        onView(screenTitleMatcher)
            .check(ViewAssertions.matches(ViewMatchers.withText(title)))
    }

    fun checkTitleOnDialogBox(title: String) {
        onView(titleOnDialogMatcher)
            .check(ViewAssertions.matches(ViewMatchers.withText(title)))
    }

    fun checkTextOnSnackBar(text: String) {
        onView(snackBarTextViewMatchers)
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    fun checkTextOnDialogBox(text: String) {
        onView(textOnDialogViewMatcher)
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    fun checkTextOnScreen(text: String) {
        onView(textOnScreen)
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }
}
