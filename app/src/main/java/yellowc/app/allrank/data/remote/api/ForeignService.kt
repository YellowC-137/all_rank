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
        @Query("method") chart_name: String = "chart.gettoptracks",
        @Query("format") format: String = "json",
        @Query("api_key") apikey: String = FOREIGN_MUSIC_API_KEY
    ): Response<ForeignResponse>
}