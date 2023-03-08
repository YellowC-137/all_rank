package yellowc.app.allrank.data.remote.response.library_response

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val endDt: String,
    val pageNo: Int,
    val pageSize: Int,
    val startDt: String
)