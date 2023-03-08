package yellowc.app.allrank.domain.models

@kotlinx.serialization.Serializable
data class ForeignMusicModel(
    val rank : Int,
    val artist_name: String,
    val track_name: String,
    val ImgUrl : String
)
