package yellowc.app.allrank.data.remote.response.library_response

data class Response(
    val docs: List<Doc>,
    val numFound: Int,
    val request: Request,
    val resultNum: Int
)