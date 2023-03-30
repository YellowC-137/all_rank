package yellowc.app.allrank.domain.models

@kotlinx.serialization.Serializable
data class MusicModel(
    val img: String,
    val rank:String,
    val link: String?,
    val artist: List<String>,
    val pudDate: String,
    val genre: String?,
    val album: String,
    val lyrics: String?,
    val title: String,
    val publisher: String
)
