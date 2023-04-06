package yellowc.app.allrank.data.remote.repositoryimpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import timber.log.Timber
import yellowc.app.allrank.data.remote.datasource.RemoteDataSource
import yellowc.app.allrank.data.remote.response.book_search_response.BookSearchResponse
import yellowc.app.allrank.data.remote.response.bookstore_response.Item
import yellowc.app.allrank.data.remote.response.boxoffice_response.WeeklyBoxOffice
import yellowc.app.allrank.data.remote.response.library_response.Doc
import yellowc.app.allrank.data.remote.response.news_search_response.NewsSearchResponse
import yellowc.app.allrank.di.DispatcherModule
import yellowc.app.allrank.domain.models.*
import yellowc.app.allrank.domain.repositories.RetrofitRepositories
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @DispatcherModule.DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : RetrofitRepositories {
    override suspend fun getBookStoreResult(): List<BookModel> {
        val result = ArrayList<BookModel>()
        withContext(dispatcherIO) {
            val responseList = async {
                remoteDataSource.getBookStore()
            }
            val bestsellers: List<Item>

            when (val response = responseList.await()) {
                is MyResult.Success -> {
                    bestsellers = response.data.item
                }
                is MyResult.Error -> {
                    return@withContext
                }
            }
            for (book in bestsellers) {
                val temp = BookModel(
                    title = book.title,
                    rank = book.rank.toString(),
                    img = book.coverLargeUrl,
                    author = book.author,
                    description = book.description,
                    link = book.link,
                    publisher = book.publisher,
                    pubDate = book.pubDate,
                    price = book.priceStandard.toString(),
                    genre = book.categoryName
                )
                result.add(temp)
            }

        }
        return result
    }

    override suspend fun getLibraryResult(start: String, end: String): List<BookModel> {
        val result = ArrayList<BookModel>()
        withContext(dispatcherIO) {
            val responseList = async {
                remoteDataSource.getLibrary(start, end)
            }
            val library: List<Doc>

            when (val response = responseList.await()) {
                is MyResult.Success -> {
                    library = response.data.response.docs
                }
                is MyResult.Error -> {
                    return@withContext
                }
            }
            for (book in library) {

                val temp = BookModel(
                    rank = book.doc.ranking,
                    title = book.doc.bookname,
                    img = book.doc.bookImageURL,
                    author = book.doc.authors,
                    genre = book.doc.class_nm,
                    link = book.doc.bookDtlUrl,
                    pubDate = book.doc.publication_year,
                    publisher = book.doc.publisher,
                    price = "",
                    description = ""
                )

                result.add(temp)
            }
        }
        return result
    }

    override suspend fun getBoxOfficeResult(target: String): List<MovieModel> {
        val result = ArrayList<MovieModel>()
        withContext(dispatcherIO) {
            val responseList = async {
                remoteDataSource.getBoxOffice(target)
            }
            val boxOffice: List<WeeklyBoxOffice>

            when (val response = responseList.await()) {
                is MyResult.Success -> {
                    boxOffice = response.data.boxOfficeResult.weeklyBoxOfficeList
                    for (movie in boxOffice) {
                        val movieInfo = async {
                            remoteDataSource.getMovieSearch(movie = movie.movieNm)
                        }
                        when (val responseMovieInfo = movieInfo.await()) {
                            is MyResult.Success -> {
                                val info = responseMovieInfo.data.items[0]
                                val temp = MovieModel(
                                    rank = movie.rank,
                                    link = info.link,
                                    title = movie.movieNm,
                                    director = info.director,
                                    pubDate = movie.openDt+" or "+info.pubDate,
                                    actors = info.actor,
                                    img = info.image
                                )
                                result.add(temp)
                            }
                            is MyResult.Error -> {
                                return@withContext
                            }
                        }
                    }
                }
                is MyResult.Error -> {
                    return@withContext
                }
            }

        }
        return result
    }

    override suspend fun getVideoResult(query: String): List<Videos> {
        val result = ArrayList<Videos>()
        withContext(dispatcherIO) {
            val responseList = async {
                remoteDataSource.getVideo(query = query)
            }
            val youtube: List<yellowc.app.allrank.data.remote.response.youtube_response.Item>

            when (val response = responseList.await()) {
                is MyResult.Success -> {
                    youtube = response.data.items
                }
                is MyResult.Error -> {
                    return@withContext
                }
            }
            for (vid in youtube) {
                val you = vid.snippet
                val temp = Videos(
                    title = you.title,
                    img = you.thumbnails.high.url,
                    date = you.publishTime,
                    channel = you.channelTitle,
                    videoId = vid.id.videoId
                )
                result.add(temp)
            }
        }
        return result
    }

    override suspend fun getNewsSearchResult(news: String): List<BaseModel> {
        val result = ArrayList<BaseModel>()
        withContext(dispatcherIO) {
            val responseList = async {
                remoteDataSource.getNewsSearch(news)
            }
            val newsData: NewsSearchResponse

            when (val response = responseList.await()) {
                is MyResult.Success -> {
                    newsData = response.data
                }
                is MyResult.Error -> {
                    return@withContext
                }
            }
            for (item in newsData.items) {
                val temp = BaseModel(
                    title = item.title,
                    rank = "",
                    img = "",
                    content = item.description,
                    owner = item.pubDate,
                    link = item.originallink
                )
                Timber.e("BOOKTEST : ${item.title}")
                result.add(temp)
            }
        }
        return result
    }

    override suspend fun getBookSearchResult(book: String): List<BookModel> {
        val result = ArrayList<BookModel>()
        withContext(dispatcherIO) {
            val responseList = async {
                remoteDataSource.getBookSearch(book)
            }
            val books: BookSearchResponse

            when (val response = responseList.await()) {
                is MyResult.Success -> {
                    books = response.data
                }
                is MyResult.Error -> {
                    return@withContext
                }
            }
            for (item in books.items) {
                val temp = BookModel(
                    title = item.title,
                    rank = "",
                    img = item.image,
                    link = item.link,
                    pubDate = item.pubdate,
                    author = item.author,
                    publisher = item.publisher,
                    price = item.discount,
                    genre = item.isbn,
                    description = item.description
                )
                Timber.e("BOOKTEST : ${item.title}")
                result.add(temp)
            }
        }
        return result
    }
}