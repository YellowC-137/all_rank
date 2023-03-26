package yellowc.app.allrank.data.remote.response.youtube_response

import kotlinx.serialization.Serializable

@Serializable
data class YoutubeResponse(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val regionCode: String
)