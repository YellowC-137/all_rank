package yellowc.app.allrank.data.remote.response.movie_response

data class BoxOfficeResult(
    val boxofficeType: String,
    val showRange: String,
    val weeklyBoxOfficeList: List<WeeklyBoxOffice>,
    val yearWeekTime: String
)