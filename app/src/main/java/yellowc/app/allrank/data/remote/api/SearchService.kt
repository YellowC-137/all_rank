package yellowc.app.allrank.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import yellowc.app.allrank.BuildConfig
import yellowc.app.allrank.data.remote.response.book_search_response.BookSearchResponse
import yellowc.app.allrank.data.remote.response.movie_search_response.MovieSearchResponse
import yellowc.app.allrank.data.remote.response.news_search_response.NewsSearchResponse
import yellowc.app.allrank.util.SEARCH_NAVER_API_URL

interface SearchService {
    @GET("${SEARCH_NAVER_API_URL}book.json")
    suspend fun getBook(
        @Header("X-Naver-Client-Id") id: String = BuildConfig.NAVER_API_KEY,
        @Header("X-Naver-Client-Secret") secret: String = BuildConfig.NAVER_SECRET,
        @Query("query") key: String
    ): Response<BookSearchResponse>

    @GET("${SEARCH_NAVER_API_URL}news.json")
    suspend fun getNews(
        @Header("X-Naver-Client-Id") id: String = BuildConfig.NAVER_API_KEY,
        @Header("X-Naver-Client-Secret") secret: String = BuildConfig.NAVER_SECRET,
        @Query("query") key: String
    ): Response<NewsSearchResponse>
}