package yellowc.app.allrank.data.remote.datasource

import yellowc.app.allrank.data.remote.response.bookstore_response.BookStoreResponse
import yellowc.app.allrank.data.remote.response.foreign_response.ForeignResponse
import yellowc.app.allrank.data.remote.response.library_response.LibraryResponse
import yellowc.app.allrank.data.remote.response.movie_response.MovieResponse
import yellowc.app.allrank.data.remote.response.news_response.NewsResponse
import yellowc.app.allrank.domain.models.MyResult

interface RemoteDataSource {

    suspend fun getTheater(targetDate: String): MyResult<MovieResponse>
    suspend fun getNews(today: String): MyResult<NewsResponse>
    suspend fun getLibrary(start: String, end: String): MyResult<LibraryResponse>
    suspend fun getForeign(): MyResult<ForeignResponse>
    suspend fun getBookStore(): MyResult<BookStoreResponse>

}