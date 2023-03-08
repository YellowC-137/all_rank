package yellowc.app.allrank.data.remote.response.movie_response

import kotlinx.serialization.Serializable

@Serializable
data class BoxOfficeResult(
    val boxofficeType: String,
    val showRange: String,
    val weeklyBoxOfficeList: List<WeeklyBoxOffice>,
    val yearWeekTime: String
)