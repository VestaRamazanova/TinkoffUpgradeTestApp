package Kaspresso_myupgradeapplication.pages

import android.widget.LinearLayout
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import io.github.kakaocup.kakao.common.builders.ViewBuilder
import com.google.android.material.textview.MaterialTextView
import com.google.android.material.snackbar.SnackbarContentLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.DialogTitle
import com.google.android.material.appbar.MaterialToolbar
import ru.tinkoff.myupgradeapplication.R

class KaspressoFirstScreen : BaseScreen() {

    val nextButton = KButton { withId(R.id.button_first) }
    val showDialogButton = KButton { withId(R.id.dialog_button) }
    val textOnScreen = KButton { withId(R.id.textview_first) }
    val changeButton = KButton { withId(R.id.change_button) }

    val titleOnDialog = KTextView {
        withParent { isInstanceOf(LinearLayout::class.java) }
        isInstanceOf(DialogTitle::class.java)
    }

    val textOnDialog = KTextView {
        withParent { isInstanceOf(LinearLayout::class.java) }
        isInstanceOf(MaterialTextView::class.java)
    }

    companion object {
        inline operator fun invoke(crossinline block: KaspressoFirstScreen.() -> Unit) =
            KaspressoFirstScreen().block()
    }
}
