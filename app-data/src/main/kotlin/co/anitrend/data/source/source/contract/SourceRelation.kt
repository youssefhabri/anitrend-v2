/*
 * Copyright (C) 2020  AniTrend
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

package co.anitrend.data.source.source.contract

import co.anitrend.arch.data.request.callback.RequestCallback
import co.anitrend.arch.data.request.contract.IRequestHelper
import co.anitrend.arch.data.source.core.SupportCoreDataSource
import co.anitrend.arch.extension.dispatchers.SupportDispatchers
import co.anitrend.data.source.model.local.SourceQuery
import co.anitrend.domain.media.contract.IMediaSourceId
import co.anitrend.domain.media.entities.MediaSourceId
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

internal abstract class SourceRelation(
    dispatchers: SupportDispatchers
) : SupportCoreDataSource(dispatchers) {
    internal lateinit var query: SourceQuery

    internal abstract val observable: Flow<IMediaSourceId>

    internal abstract suspend fun getSourceRelation(
        query: SourceQuery,
        callback: RequestCallback
    )

    operator fun invoke(sourceQuery: SourceQuery): Flow<IMediaSourceId> {
        query = sourceQuery
        launch(coroutineContext, CoroutineStart.LAZY) {
            requestHelper.runIfNotRunning(
                IRequestHelper.RequestType.INITIAL
            ) { getSourceRelation(sourceQuery, it) }
        }
        return observable
    }
}