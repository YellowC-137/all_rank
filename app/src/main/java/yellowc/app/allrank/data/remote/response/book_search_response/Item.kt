package yellowc.app.allrank.data.remote.response.book_search_response

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val author: String,
    val description: String,
    val discount: String,
    val image: String,
    val isbn: String,
    val link: String,
    val pubdate: String,
    val publisher: String,
    val title: String
)