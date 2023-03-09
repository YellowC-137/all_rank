package yellowc.app.allrank.data.remote.datasourceimpl

import yellowc.app.allrank.data.remote.api.*
import yellowc.app.allrank.data.remote.datasource.RemoteDataSource
import yellowc.app.allrank.data.remote.response.bookstore_response.BookStoreResponse
import yellowc.app.allrank.data.remote.response.foreign_response.ForeignResponse
import yellowc.app.allrank.data.remote.response.library_response.LibraryResponse
import yellowc.app.allrank.data.remote.response.movie_response.MovieResponse
import yellowc.app.allrank.data.remote.response.news_response.NewsResponse
import yellowc.app.allrank.domain.models.MyResult
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val bookStoreService: BookStoreService,
    private val theaterService: TheaterService,
    private val libraryService: LibraryService,
    private val newsService: NewsService
) : RemoteDataSource {
    override suspend fun getTheater(targetDate: String): MyResult<MovieResponse> {
        val response = theaterService.getTheater(targetDt = targetDate)
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

    override suspend fun getNews(today: String): MyResult<NewsResponse> {
        val response = newsService.getNews(date = today)
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
}