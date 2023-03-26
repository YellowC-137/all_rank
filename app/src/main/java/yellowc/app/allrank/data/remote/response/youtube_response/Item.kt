package yellowc.app.allrank.data.remote.response.youtube_response

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val etag: String,
    val id: Id,
    val kind: String,
    val snippet: Snippet
)