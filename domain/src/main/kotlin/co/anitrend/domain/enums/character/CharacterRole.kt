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

package co.anitrend.domain.enums.character

import co.anitrend.domain.common.enum.IGraphEnum

/**
 * The role the character plays in the media
 */
enum class CharacterRole(override val value: String) : IGraphEnum {
    /** A primary character role in the media */
    MAIN("MAIN"),
    /** A supporting character role in the media */
    SUPPORTING("SUPPORTING"),
    /** A background character in the media */
    BACKGROUND("BACKGROUND")
}