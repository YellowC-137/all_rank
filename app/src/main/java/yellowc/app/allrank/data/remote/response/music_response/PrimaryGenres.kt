package yellowc.app.allrank.data.remote.response.music_response

import kotlinx.serialization.Serializable

@Serializable
data class PrimaryGenres(
    val music_genre_list: List<MusicGenre>
)