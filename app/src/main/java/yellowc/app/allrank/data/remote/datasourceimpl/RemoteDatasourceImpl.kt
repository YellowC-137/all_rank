package yellowc.app.allrank.data.remote.datasourceimpl

import yellowc.app.allrank.BuildConfig
import yellowc.app.allrank.data.remote.api.BookStoreService
import yellowc.app.allrank.data.remote.api.BoxOfficeService
import yellowc.app.allrank.data.remote.api.LibraryService
import yellowc.app.allrank.data.remote.api.MovieSearchService
import yellowc.app.allrank.data.remote.datasource.RemoteDataSource
import yellowc.app.allrank.data.remote.response.bookstore_response.BookStoreResponse
import yellowc.app.allrank.data.remote.response.boxoffice_response.BoxOfficeResponse
import yellowc.app.allrank.data.remote.response.library_response.LibraryResponse
import yellowc.app.allrank.data.remote.response.movie_response.MovieResponse
import yellowc.app.allrank.domain.models.MyResult
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val bookStoreService: BookStoreService,
    private val boxOfficeService: BoxOfficeService,
    private val libraryService: LibraryService,
    private val movieSearchService: MovieSearchService
) : RemoteDataSource {
    override suspend fun getBoxOffice(targetDate: String): MyResult<BoxOfficeResponse> {
        val response = boxOfficeService.getTheater(targetDt = targetDate)
        return try {
            if (response.isSuccessful) {
                MyResult.Success(response.body()!!)
            } else {
                MyResult.Error(IllegalArgumentException("ERROR"))
            }
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }

    override suspend fun getLibrary(start: String, end: String): MyResult<LibraryResponse> {
        val response = libraryService.getLibrary(startDt = start, endDt = end)
        return try {
            if (response.isSuccessful) {
                MyResult.Success(response.body()!!)
            } else {
                MyResult.Error(IllegalArgumentException("ERROR"))
            }
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }

    override suspend fun getBookStore(): MyResult<BookStoreResponse> {
        val response = bookStoreService.getBookStore()
        return try {
            if (response.isSuccessful) {
                MyResult.Success(response.body()!!)
            } else {
                MyResult.Error(IllegalArgumentException("ERROR"))
            }
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }

    override suspend fun getMovie(movie: String): MyResult<MovieResponse> {
        val response = movieSearchService.getTheater(key=movie, id=BuildConfig.NAVER_API_KEY, secret = BuildConfig.NAVER_SECRET)
        return try {
            if (response.isSuccessful) {
                MyResult.Success(response.body()!!)
            } else {
                MyResult.Error(IllegalArgumentException("ERROR"))
            }
        } catch (e: Exception) {
            MyResult.Error(e)
        }
    }
}