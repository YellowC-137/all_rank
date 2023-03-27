package yellowc.app.allrank.domain.models

@kotlinx.serialization.Serializable
data class BookModel(
    val img: String,
    val title: String,
    val pubDate: String,
    val author: String,
    val description: String,
    val publisher: String
)