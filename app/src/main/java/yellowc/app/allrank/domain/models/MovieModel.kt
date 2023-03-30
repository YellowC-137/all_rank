package yellowc.app.allrank.domain.models

@kotlinx.serialization.Serializable
data class MovieModel(
    val rank:String,
    val link: String?,
    val title:String,
    val director:String,
    val pubDate:String?,
    val actors:String,
    val img: String
)
