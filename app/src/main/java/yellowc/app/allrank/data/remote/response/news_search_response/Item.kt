package yellowc.app.allrank.data.remote.response.news_search_response

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val description: String,
    val link: String,
    val originallink: String,
    val pubDate: String,
    val title: String
)