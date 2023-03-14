package yellowc.app.allrank.data.remote.response.boxoffice_response

import kotlinx.serialization.Serializable

@Serializable
data class BoxOfficeResponse(
    val boxOfficeResult: BoxOfficeResult
)