package yellowc.app.allrank.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import yellowc.app.allrank.BuildConfig.LIBRARY_API_KEY
import yellowc.app.allrank.data.remote.response.library_response.LibraryResponse
import yellowc.app.allrank.util.LIBRARY_API_URL

interface LibraryService {
    @GET("${LIBRARY_API_URL}loanItemSrch")
    suspend fun getLibrary(
        @Query("authKey") authKey: String = LIBRARY_API_KEY,
        @Query("startDt") startDt: String,
        @Query("endDt") endDt: String,
        @Query("pageNo") pageNo: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("format") format: String = "json"
    ): Response<LibraryResponse>
}