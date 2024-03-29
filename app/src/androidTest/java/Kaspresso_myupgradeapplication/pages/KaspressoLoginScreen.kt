package Kaspresso_myupgradeapplication.pages

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KSnackbar
import io.github.kakaocup.kakao.common.builders.ViewBuilder
import com.google.android.material.textview.MaterialTextView
import com.google.android.material.snackbar.SnackbarContentLayout
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.appbar.MaterialToolbar
import ru.tinkoff.myupgradeapplication.R

class KaspressoLoginScreen : KScreen<KaspressoLoginScreen>() {
    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val buttonPrevious = KButton { withId(R.id.button_second) }
    val buttonSubmit = KButton { withId(R.id.button_submit) }
    val loginField = KEditText { withId(R.id.edittext_login) }
    val passwordField = KEditText { withId(R.id.edittext_password) }
    val editTextLogin = KEditText { withId(R.id.edittext_login) }
    val editTextPassword = KEditText { withId(R.id.edittext_password) }
    val snackbar = KSnackbar()

//   val textOnSnackbar = KTextView {
//        isInstanceOf(MaterialTextView::class.java)
//        withParent { isInstanceOf(SnackbarContentLayout::class.java) }
//    }

    companion object {
        inline operator fun invoke(crossinline block: KaspressoLoginScreen.() -> Unit) =
            KaspressoLoginScreen().block()
    }
}

