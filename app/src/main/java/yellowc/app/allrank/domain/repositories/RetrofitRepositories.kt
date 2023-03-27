package yellowc.app.allrank.domain.repositories

import yellowc.app.allrank.data.remote.response.book_search_response.BookSearchResponse
import yellowc.app.allrank.data.remote.response.movie_search_response.MovieSearchResponse
import yellowc.app.allrank.data.remote.response.news_search_response.NewsSearchResponse
import yellowc.app.allrank.domain.models.*

interface RetrofitRepositories {
    suspend fun getBookStoreResult(): List<BaseModel>
    suspend fun getLibraryResult(start: String, end: String): List<BaseModel>
    suspend fun getBoxOfficeResult(target: String): List<BaseModel>
    suspend fun getVideoResult(query: String): List<Videos>
    suspend fun getNewsSearchResult(news: String): List<NewsModel>
    suspend fun getBookSearchResult(book: String): List<BookModel>
}