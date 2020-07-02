/*
 * Copyright (C) 2019  AniTrend
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package co.anitrend.core.extensions

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.LifecycleOwner
import co.anitrend.core.AniTrendApplication
import co.anitrend.core.R
import co.anitrend.core.ui.fragment.model.FragmentItem
import co.anitrend.data.arch.AniTrendExperimentalFeature
import com.afollestad.materialdialogs.DialogBehavior
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import org.koin.androidx.scope.lifecycleScope
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import timber.log.Timber


const val moduleTag = "CoreExtensions"

/**
 * Text separator character
 */
const val CHARACTER_SEPARATOR: Char = '•'

@AniTrendExperimentalFeature
fun FragmentActivity.recreateModules() {
    val coreApplication = applicationContext as AniTrendApplication
    runCatching {
        coreApplication.restartDependencyInjection()
    }.onFailure {
        Timber.tag(moduleTag).e(it)
    }
}

/**
 * Creates a default dialog with a lifecycle already attached to it and will not dismiss
 * when the user touches outside the view
 */
fun FragmentActivity?.createDialog(
    dialogBehavior: DialogBehavior = MaterialDialog.DEFAULT_BEHAVIOR
) = this?.run {
    MaterialDialog(this, dialogBehavior)
        .lifecycleOwner(this)
        .cancelOnTouchOutside(false)
}

/**
 * Check if the system is in night mode
 */
fun Context.isEnvironmentNightMode() =
    resources.getBoolean(R.bool.isNightMode)

/**
 * Check if the system should use light status bar
 */
fun Context.isLightStatusBar() =
    resources.getBoolean(R.bool.isLightStatusBar)

/**
 * Check if the system should use light navigation bar
 */
fun Context.isLightNavigationBar() =
    resources.getBoolean(R.bool.isLightNavigationBar)

fun FragmentActivity.makeStatusBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}

fun FragmentActivity.hideStatusBarAndNavigationBar() {
    window.apply {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}

/**
 * Inject using lifecycle scope
 */
inline fun <reified T: Any> LifecycleOwner.injectScoped(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy { lifecycleScope.get<T>(qualifier, parameters) }

/**
 * Checks for existing fragment in [FragmentManager], if one exists that is used otherwise
 * a new instance is created.
 *
 * @return tag of the fragment
 *
 * @see androidx.fragment.app.commit
 */
inline fun FragmentItem<*>.commit(
    @IdRes contentFrame: Int,
    fragmentActivity: FragmentActivity,
    action: FragmentTransaction.() -> Unit
) : String? {
    val fragmentManager = fragmentActivity.supportFragmentManager

    val fragmentTag = tag()
    val backStack = fragmentManager.findFragmentByTag(fragmentTag)

    fragmentManager.commit {
        action()
        backStack?.let {
            replace(contentFrame, it, fragmentTag)
        } ?: replace(contentFrame, fragment, parameter, fragmentTag)
    }
    return fragmentTag
}

/**
 * Checks for existing fragment in [FragmentManager], if one exists that is used otherwise
 * a new instance is created.
 *
 * @return tag of the fragment
 *
 * @see androidx.fragment.app.commit
 */
inline fun FragmentItem<*>.commit(
    contentFrame: View,
    fragmentActivity: FragmentActivity,
    action: FragmentTransaction.() -> Unit
) = commit(contentFrame.id, fragmentActivity, action)