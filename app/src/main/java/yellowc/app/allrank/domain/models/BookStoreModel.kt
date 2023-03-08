package yellowc.app.allrank.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@kotlinx.serialization.Serializable
data class BookStoreModel(
    val rank : Int,
    val title:String,
    val description : String,
    val pubDate : String,
    val imgUrl : String,
    val publisher : String,
    val author : String
)
