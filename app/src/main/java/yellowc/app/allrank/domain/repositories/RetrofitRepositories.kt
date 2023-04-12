package yellowc.app.allrank.domain.repositories

import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.models.BookModel
import yellowc.app.allrank.domain.models.MovieModel
import yellowc.app.allrank.domain.models.Videos

interface RetrofitRepositories {
    suspend fun getBookStoreResult(): List<BookModel>
    suspend fun getLibraryResult(start: String, end: String): List<BookModel>
    suspend fun getBoxOfficeResult(target: String): List<MovieModel>
    suspend fun getVideoResult(query: String): List<Videos>
    suspend fun getNewsSearchResult(news: String): List<BaseModel>
    suspend fun getBookSearchResult(book: String): List<BookModel>
    //게임,음악
}