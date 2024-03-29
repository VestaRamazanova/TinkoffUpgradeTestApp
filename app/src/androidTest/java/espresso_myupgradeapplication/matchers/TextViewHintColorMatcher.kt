package espresso_myupgradeapplication.matchers

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import androidx.test.platform.app.InstrumentationRegistry
import androidx.core.content.ContextCompat

class TextViewHintColorMatcher(@ColorRes private val expectedColorResId: Int) : BoundedMatcher<View, TextView>(TextView::class.java) {

    override fun describeTo(description: Description) {
        // Получение фактического цвета из ресурса
        description.appendText("TextView with expected hint text color: ${getHexColor(expectedColorResId)}")
    }

    override fun matchesSafely(textView: TextView): Boolean {
        val context = textView.context
        val expectedColor = ContextCompat.getColor(context, expectedColorResId)
        return textView.currentHintTextColor == expectedColor
    }

    @Suppress("MagicNumber")
    private fun getHexColor(@ColorRes colorResId: Int): String {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val color = ContextCompat.getColor(context, colorResId)
        return String.format("#%06X", 0xFFFFFF and color)
    }
}
