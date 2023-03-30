package yellowc.app.allrank.domain.models

@kotlinx.serialization.Serializable
data class GameModel(
    val rank:String,
    val link: String?,
    val img: String,
    val title: String,
    val publisher: String,
    val genre: String,
    val price: String,
    val platform: String,
    val description: String?,
    val pubdate: String
)
