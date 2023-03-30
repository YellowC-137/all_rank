package yellowc.app.allrank.domain.models
@kotlinx.serialization.Serializable
data class RelateModel(
    val img: String,
    val name: String,
    val age: String?,
    val role:String?
)