package yellowc.app.allrank.data.remote.datasourceimpl

import yellowc.app.allrank.BuildConfig
import yellowc.app.allrank.data.remote.api.*
import yellowc.app.allrank.data.remote.datasource.RemoteDataSource
import yellowc.app.allrank.data.remote.response.book_search_response.BookSearchResponse
import yellowc.app.allrank.data.remote.response.bookstore_response.BookStoreResponse
import yellowc.app.allrank.data.remote.response.boxoffice_response.BoxOfficeResponse
import yellowc.app.allrank.data.remote.response.library_response.LibraryResponse
import yellowc.app.allrank.data.remote.response.news_search_response.NewsSearchResponse
import yellowc.app.allrank.data.remote.response.youtube_response.YoutubeResponse
import yellowc.app.allrank.domain.models.MyResult
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val bookStoreService: BookStoreService,
    private val boxOfficeService: BoxOfficeService,
    private val libraryService: LibraryService,
    private val searchService: SearchService,
    private val youTubeService: YouTubeService
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

    override suspend fun getVideo(query: String): MyResult<YoutubeResponse> {
        val response = youTubeService.getVideo(query = query)
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

    override suspend fun getNewsSearch(news: String): MyResult<NewsSearchResponse> {
        val response = searchService.getNews(
            key = news,
            id = BuildConfig.NAVER_API_KEY,
            secret = BuildConfig.NAVER_SECRET
        )
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

    override suspend fun getBookSearch(book: String): MyResult<BookSearchResponse> {
        val response = searchService.getBook(
            key = book,
            id = BuildConfig.NAVER_API_KEY,
            secret = BuildConfig.NAVER_SECRET
        )
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