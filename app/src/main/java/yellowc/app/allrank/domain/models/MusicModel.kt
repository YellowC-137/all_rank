package yellowc.app.allrank.domain.models

@kotlinx.serialization.Serializable
data class MusicModel(
    val img: String,
    val rank: String,
    val artist: List<String>,
    val pudDate: String,
    val genre: String,
    val album: String,
    val lyrics: String,
    val description: String,
    val title: String,
    val company: String
)
