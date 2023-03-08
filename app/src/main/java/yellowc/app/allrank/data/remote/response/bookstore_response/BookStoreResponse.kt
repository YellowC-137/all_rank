package yellowc.app.allrank.data.remote.response.bookstore_response

import kotlinx.serialization.Serializable

@Serializable
data class BookStoreResponse(
    val copyright: String,
    val imageUrl: String,
    val item: List<Item>,
    val itemsPerPage: Int,
    val language: String,
    val link: String,
    val maxResults: Int,
    val pubDate: String,
    val queryType: String,
    val returnCode: String,
    val returnMessage: String,
    val searchCategoryId: String,
    val searchCategoryName: String,
    val startIndex: Int,
    val title: String,
    val totalResults: Int // 총 아이템수
)