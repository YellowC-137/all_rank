package yellowc.app.allrank.domain.models
@kotlinx.serialization.Serializable
data class Videos(
    val img: String,
    val title: String,
    val channel: String,
    val videoId: String,
    val date: String
)
