package yellowc.app.allrank.data.remote.response.movie_response

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val boxOfficeResult: BoxOfficeResult
)