package yellowc.app.allrank.data.remote.datasource

import yellowc.app.allrank.data.remote.response.bookstore_response.BookStoreResponse
import yellowc.app.allrank.data.remote.response.library_response.LibraryResponse
import yellowc.app.allrank.data.remote.response.boxoffice_response.BoxOfficeResponse
import yellowc.app.allrank.data.remote.response.movie_response.MovieResponse
import yellowc.app.allrank.data.remote.response.youtube_response.YoutubeResponse
import yellowc.app.allrank.domain.models.MyResult

interface RemoteDataSource {

    suspend fun getBoxOffice(targetDate: String): MyResult<BoxOfficeResponse>
    suspend fun getLibrary(start: String, end: String): MyResult<LibraryResponse>
    suspend fun getBookStore(): MyResult<BookStoreResponse>
    suspend fun getMovie(movie: String): MyResult<MovieResponse>
    suspend fun getVideo(query: String): MyResult<YoutubeResponse>
}