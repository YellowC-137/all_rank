package yellowc.app.allrank.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import yellowc.app.allrank.data.remote.response.foreign_response.ForeignResponse
import yellowc.app.allrank.util.FOREIGN_MUSIC_API_URL
import yellowc.app.allrank.BuildConfig.FOREIGN_MUSIC_API_KEY

interface ForeignService {
    @GET("${FOREIGN_MUSIC_API_URL}chart.tracks.get")
    suspend fun getForeign(
        @Query("chart_name") chart_name: String = "mxmweekly",
        @Query("page") page: Int = 1,
        @Query("page_size") page_size: Int = 100,
        @Query("country") country: String = "WX",
        @Query("format") format: String = "json",
        @Query("apikey") apikey: String = FOREIGN_MUSIC_API_KEY
    ): Response<ForeignResponse>
}