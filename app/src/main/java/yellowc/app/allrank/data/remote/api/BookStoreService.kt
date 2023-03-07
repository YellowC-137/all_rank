package yellowc.app.allrank.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import yellowc.app.allrank.BuildConfig.BOOK_STORE_API_KEY
import yellowc.app.allrank.data.remote.response.bookstore_response.BookStoreResponse
import yellowc.app.allrank.util.BOOK_STORE_API_URL

interface BookStoreService {
    @GET("${BOOK_STORE_API_URL}bestSeller.api")
    suspend fun getBookStore(
        @Query("key") key: String = BOOK_STORE_API_KEY,
        @Query("categoryId") categoryId: String = "100",
        @Query("output") output: String = "json"
    ): Response<BookStoreResponse>
}