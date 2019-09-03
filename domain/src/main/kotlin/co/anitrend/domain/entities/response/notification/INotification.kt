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

package co.anitrend.domain.entities.response.notification

import co.anitrend.domain.common.entity.IEntity
import co.anitrend.domain.enums.notification.NotificationType

/** [Notification](https://anilist.github.io/ApiV2-GraphQL-Docs/notificationunion.doc.html)
 * Notification contract with shared objects
 *
 * @property context The notification context text
 * @property createdAt The time the notification was created at
 * @property type The type of notification
 */
interface INotification : IEntity {
    val context: String?
    val createdAt: Long?
    val type: NotificationType?
}