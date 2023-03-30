package yellowc.app.allrank.data.remote.response.music_response

import kotlinx.serialization.Serializable

@Serializable
data class Lyrics(
    val explicit: Int,
    val lyrics_body: String,
    val lyrics_copyright: String,
    val lyrics_id: Int,
    val pixel_tracking_url: String,
    val script_tracking_url: String,
    val updated_time: String
)