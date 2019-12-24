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

package co.anitrend.core.ui.activity

import co.anitrend.arch.core.presenter.SupportPresenter
import co.anitrend.arch.ui.activity.SupportActivity
import co.anitrend.core.util.config.ConfigurationUtil
import org.koin.android.ext.android.inject

/**
 * Abstract application based activity for anitrend, avoids further modification of the
 * support library, any feature additions should be added through extensions
 */
abstract class AnitrendActivity<M, P: SupportPresenter<*>> : SupportActivity<M, P>() {

    protected val configurationUtil by inject<ConfigurationUtil>()

    /**
     * Can be used to configure custom theme styling as desired
     */
    override fun configureActivity() {
        configurationUtil.onCreate(this)
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are *not* resumed.
     */
    override fun onResume() {
        super.onResume()
        configurationUtil.onResume(this)
    }

    /**
     * Handles the complex logic required to dispatch network request to [co.anitrend.arch.core.viewmodel.contract.ISupportViewModel]
     * to either request from the network or database cache.
     *
     * The results of the dispatched network or cache call will be published by the
     * [androidx.lifecycle.LiveData] specifically [co.anitrend.arch.core.viewmodel.contract.ISupportViewModel.model]
     *
     * @see [co.anitrend.arch.core.viewmodel.contract.ISupportViewModel.invoke]
     */
    override fun onFetchDataInitialize() {
        // may not be used in most activities so we're making it optional
    }
}