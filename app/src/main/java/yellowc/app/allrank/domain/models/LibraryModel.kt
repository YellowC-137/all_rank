package yellowc.app.allrank.domain.models

@kotlinx.serialization.Serializable
data class LibraryModel(
    val rank:String,
    val publisher: String,
    val publication_year: String,
    val bookImageURL: String,
    val authors: String,
    val bookname: String,
    val class_nm: String,
)
