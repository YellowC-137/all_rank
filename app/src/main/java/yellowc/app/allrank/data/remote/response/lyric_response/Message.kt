package yellowc.app.allrank.data.remote.response.lyric_response

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val body: Body,
    val header: Header
)