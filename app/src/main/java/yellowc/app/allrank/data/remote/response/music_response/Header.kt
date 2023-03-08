package yellowc.app.allrank.data.remote.response.music_response

import kotlinx.serialization.Serializable

@Serializable
data class Header(
    val execute_time: Double,
    val status_code: Int
)