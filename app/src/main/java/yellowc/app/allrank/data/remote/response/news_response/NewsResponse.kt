package yellowc.app.allrank.data.remote.response.news_response

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)