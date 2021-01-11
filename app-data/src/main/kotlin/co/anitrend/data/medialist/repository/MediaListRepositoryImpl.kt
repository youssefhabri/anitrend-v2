/*
 * Copyright (C) 2021  AniTrend
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

package co.anitrend.data.medialist.repository

import androidx.paging.PagedList
import co.anitrend.arch.data.repository.SupportRepository
import co.anitrend.arch.data.state.DataState
import co.anitrend.arch.data.state.DataState.Companion.create
import co.anitrend.data.medialist.source.paged.contract.MediaListPagedSource
import co.anitrend.domain.common.graph.IGraphPayload
import co.anitrend.domain.medialist.entity.MediaList
import co.anitrend.domain.medialist.repository.MediaListRepository

internal class MediaListRepositoryImpl(
    private val source: MediaListPagedSource
) : SupportRepository(source), MediaListRepository<DataState<PagedList<MediaList>>> {

    override fun getMediaListPaged(
        query: IGraphPayload
    ) = source create source.invoke(query)
}