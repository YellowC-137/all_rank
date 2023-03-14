package yellowc.app.allrank.data.remote.response.movie_response

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val subtitle: String,
    val title: String,
    val userRating: String
)