package yellowc.app.allrank.data.remote.datasource

import yellowc.app.allrank.data.remote.response.book_search_response.BookSearchResponse
import yellowc.app.allrank.data.remote.response.bookstore_response.BookStoreResponse
import yellowc.app.allrank.data.remote.response.boxoffice_response.BoxOfficeResponse
import yellowc.app.allrank.data.remote.response.library_response.LibraryResponse
import yellowc.app.allrank.data.remote.response.movie_search_response.MovieSearchResponse
import yellowc.app.allrank.data.remote.response.news_search_response.NewsSearchResponse
import yellowc.app.allrank.data.remote.response.youtube_response.YoutubeResponse
import yellowc.app.allrank.domain.models.MyResult

interface RemoteDataSource {

    suspend fun getBoxOffice(targetDate: String): MyResult<BoxOfficeResponse>
    suspend fun getLibrary(start: String, end: String): MyResult<LibraryResponse>
    suspend fun getBookStore(): MyResult<BookStoreResponse>
    suspend fun getVideo(query: String): MyResult<YoutubeResponse>
    suspend fun getNewsSearch(news: String): MyResult<NewsSearchResponse>
    suspend fun getBookSearch(book: String): MyResult<BookSearchResponse>
}