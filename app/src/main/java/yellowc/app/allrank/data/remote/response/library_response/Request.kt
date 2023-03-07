package yellowc.app.allrank.data.remote.response.library_response

data class Request(
    val endDt: String,
    val pageNo: Int,
    val pageSize: Int,
    val startDt: String
)