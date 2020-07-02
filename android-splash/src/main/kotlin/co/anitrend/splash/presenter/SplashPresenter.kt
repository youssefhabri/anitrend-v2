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

package co.anitrend.splash.presenter

import android.content.Context
import androidx.fragment.app.FragmentActivity
import co.anitrend.core.presenter.CorePresenter
import co.anitrend.core.settings.Settings
import co.anitrend.navigation.NavigationTargets
import kotlinx.coroutines.delay

class SplashPresenter(
    context: Context,
    settings: Settings
) : CorePresenter(context, settings) {

    /**
     * Decide which screen to open
     */
    suspend fun firstRunCheck(activity: FragmentActivity?) {
        delay(1200)
        if (settings.isNewInstallation)
            NavigationTargets.OnBoarding(context).invoke()
        else
            NavigationTargets.Main(context).invoke()
        activity?.finish()
    }
}