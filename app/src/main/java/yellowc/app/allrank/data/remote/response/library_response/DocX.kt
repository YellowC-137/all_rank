package yellowc.app.allrank.data.remote.response.library_response

import kotlinx.serialization.Serializable

@Serializable
data class DocX(
    val addition_symbol: String,
    val authors: String,
    val bookDtlUrl: String,
    val bookImageURL: String,
    val bookname: String,
    val class_nm: String,
    val class_no: String,
    val isbn13: String,
    val loan_count: String,
    val no: Int,
    val publication_year: String,
    val publisher: String,
    val ranking: String,
    val vol: String
)