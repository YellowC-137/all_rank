package yellowc.app.allrank.data.remote.response.game_search_response

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val aliases: String,
    val api_detail_url: String,
    val date_added: String,
    val date_last_updated: String,
    val deck: String,
    val description: String,
    val expected_release_day: String,
    val expected_release_month: String,
    val expected_release_quarter: String,
    val expected_release_year: String,
    val guid: String,
    val id: Int,
    val image: Image,
    val image_tags: List<ImageTag>,
    val name: String,
    val number_of_user_reviews: Int,
    val original_game_rating: List<OriginalGameRating>,
    val original_release_date: String,
    val platforms: List<Platform>,
    val resource_type: String,
    val site_detail_url: String
)