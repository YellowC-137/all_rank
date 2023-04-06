package yellowc.app.allrank.data.remote.response.music_response

import kotlinx.serialization.Serializable

@Serializable
data class Results(
    val attr: Attr,
    val Query: OpensearchQuery,
    val itemsPerPage: String,
    val startIndex: String,
    val totalResults: String,
    val trackmatches: Trackmatches
)