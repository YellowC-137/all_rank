package yellowc.app.allrank.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import yellowc.app.allrank.BuildConfig.NEWS_API_KEY
import yellowc.app.allrank.data.remote.response.news_response.NewsResponse
import yellowc.app.allrank.util.NEWS_API_URL

interface NewsService {
    @GET("${NEWS_API_URL}top-headlines")
    suspend fun getNews(
        @Query("from") date: String,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = NEWS_API_KEY,
        @Query("country") country: String = "kr"
    ): Response<NewsResponse>
}