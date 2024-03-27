package Kaspresso_myupgradeapplication.pages

import com.kaspersky.kaspresso.screens.KScreen

abstract class BaseScreen : KScreen<BaseScreen>() {
    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null
}
