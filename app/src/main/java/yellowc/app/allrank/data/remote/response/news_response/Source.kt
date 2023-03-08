package yellowc.app.allrank.data.remote.response.news_response

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String,
    val name: String
)