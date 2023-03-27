package yellowc.app.allrank.domain.models

@kotlinx.serialization.Serializable
data class MovieModel(
    val title:String,
    val director:String,
    val pubDate:String?,
    val rank:String,
    val actors:List<String>,
    val genre:String?,
    val img: String,
    val rate:String?

)
