package yellowc.app.allrank.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import yellowc.app.allrank.BuildConfig
import yellowc.app.allrank.data.remote.response.movie_response.MovieResponse
import yellowc.app.allrank.util.MOVIE_SEARCH_NAVER_API_URL

interface MovieSearchService {
    @GET("${MOVIE_SEARCH_NAVER_API_URL}movie.json")
    suspend fun getTheater(
        @Header("X-Naver-Client-Id") id: String = BuildConfig.NAVER_API_KEY,
        @Header("X-Naver-Client-Secret") secret: String = BuildConfig.NAVER_SECRET,
        @Query("query") key: String
    ): Response<MovieResponse>
}