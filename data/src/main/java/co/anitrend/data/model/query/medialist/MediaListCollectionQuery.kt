package co.anitrend.data.model.query.medialist

import co.anitrend.data.model.contract.FuzzyDateInt
import co.anitrend.data.model.contract.FuzzyDateLike
import co.anitrend.data.model.contract.IGraphQuery
import co.anitrend.data.repository.media.attributes.MediaType
import co.anitrend.data.repository.medialist.attributes.MediaListSort

/** [MediaListCollection query][https://anilist.github.io/ApiV2-GraphQL-Docs/query.doc.html]
 *
 * Media list collection query, provides list pre-grouped by status & custom lists.
 *
 * @param userId Filter by a user's id
 * @param userName Filter by a user's name
 * @param type Filter by the list entries media type
 * @param notes Filter by note words and #tags
 * @param startedAt Filter by the date the user started the media
 * @param completedAt Filter by the date the user completed the media
 * @param forceSingleCompletedList Always return completed list entries in one group, overriding the user's split completed option.
 * @param notes_like Filter by note words and #tags
 * @param startedAt_greater Filter by the date the user started the media
 * @param startedAt_lesser Filter by the date the user started the media
 * @param startedAt_like Filter by the date the user started the media
 * @param completedAt_greater Filter by the date the user completed the media
 * @param completedAt_lesser Filter by the date the user completed the media
 * @param completedAt_like Filter by the date the user completed the media
 * @param sort The order the results will be returned in
 */
data class MediaListCollectionQuery(
    val userId: Int? = null,
    val userName: String? = null,
    val type: MediaType,
    val notes: String? = null,
    val startedAt: FuzzyDateInt? = null,
    val completedAt: FuzzyDateInt? = null,
    val forceSingleCompletedList: Boolean? = null,
    val notes_like: String? = null,
    val startedAt_greater: FuzzyDateInt? = null,
    val startedAt_lesser: FuzzyDateInt? = null,
    val startedAt_like: FuzzyDateLike? = null,
    val completedAt_greater: FuzzyDateInt? = null,
    val completedAt_lesser: FuzzyDateInt? = null,
    val completedAt_like: FuzzyDateLike? = null,
    val sort: List<MediaListSort>? = null
) : IGraphQuery {

    /**
     * A map serializer to build maps out of object so that we can consume them
     * using [io.github.wax911.library.model.request.QueryContainerBuilder].
     */
    override fun toMap() = mapOf(
        "userId" to userId,
        "userName" to userName,
        "type" to type,
        "notes" to notes,
        "startedAt" to startedAt,
        "completedAt" to completedAt,
        "forceSingleCompletedList" to forceSingleCompletedList,
        "notes_like" to notes_like,
        "startedAt_greater" to startedAt_greater,
        "startedAt_lesser" to startedAt_lesser,
        "startedAt_like" to startedAt_like,
        "completedAt_greater" to completedAt_greater,
        "completedAt_lesser" to completedAt_lesser,
        "completedAt_like" to completedAt_like,
        "sort" to sort
    )
}