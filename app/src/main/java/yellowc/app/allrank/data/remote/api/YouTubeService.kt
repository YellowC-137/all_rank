package yellowc.app.allrank.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import yellowc.app.allrank.BuildConfig
import yellowc.app.allrank.data.remote.response.youtube_response.YoutubeResponse
import yellowc.app.allrank.util.YOUTUBE_API_URL

interface YouTubeService {
    @GET("${YOUTUBE_API_URL}search")
    suspend fun getVideo(
        @Query("part") part: String = "snippet",
        @Query("key") authKey: String = BuildConfig.YOUTUBE_API_KEY,
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("channelType") channelType: String = "any",
    ): Response<YoutubeResponse>
}