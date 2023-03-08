package yellowc.app.allrank.data.remote.response.foreign_response

import kotlinx.serialization.Serializable

@Serializable
data class Attr(
    val page: String,
    val perPage: String,
    val total: String,
    val totalPages: String
)