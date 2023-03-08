package yellowc.app.allrank.data.remote.response.news_response

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)