package yellowc.app.allrank.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class BookModel(
    val rank: String,
    val link: String?,
    val img: String,
    val title: String,
    val pubDate: String,
    val author: String,
    val description: String,
    val publisher: String,
    val price: String?,
    val genre: String?
) : java.io.Serializable