package yellowc.app.allrank.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import yellowc.app.allrank.BuildConfig.MOVIE_API_KEY
import yellowc.app.allrank.data.remote.response.movie_response.MovieResponse
import yellowc.app.allrank.util.MOVIE_BOXOFFICE_API_URL

interface TheaterService {
    @GET("${MOVIE_BOXOFFICE_API_URL}searchWeeklyBoxOfficeList.json")
    suspend fun getTheater(
        @Query("key") key:String = MOVIE_API_KEY,
        @Query("weekGb") weekGb:String = "0",
        @Query("targetDt")targetDt:String
    ): Response<MovieResponse>
}