package yellowc.app.allrank.data.remote.response.library_response

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val docs: List<Doc>,
    val numFound: Int,
    val request: Request,
    val resultNum: Int
)