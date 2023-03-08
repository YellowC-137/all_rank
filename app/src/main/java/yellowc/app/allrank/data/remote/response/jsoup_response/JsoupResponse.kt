package yellowc.app.allrank.data.remote.response.jsoup_response

import kotlinx.serialization.Serializable

@Serializable
data class JsoupResponse(
    val rank : Int,
    val title : String,
    val ImgUrl :String,
    val description: String?
)
