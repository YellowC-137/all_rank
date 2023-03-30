package yellowc.app.allrank.data.remote.response.music_response

data class Results(
    val @attr: Attr,
    val opensearch:Query: OpensearchQuery,
    val opensearch:itemsPerPage: String,
    val opensearch:startIndex: String,
    val opensearch:totalResults: String,
    val trackmatches: Trackmatches
)