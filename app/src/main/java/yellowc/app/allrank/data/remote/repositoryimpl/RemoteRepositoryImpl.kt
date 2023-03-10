package yellowc.app.allrank.data.remote.repositoryimpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import yellowc.app.allrank.data.remote.datasource.RemoteDataSource
import yellowc.app.allrank.data.remote.response.bookstore_response.Item
import yellowc.app.allrank.data.remote.response.foreign_response.Track
import yellowc.app.allrank.data.remote.response.library_response.Doc
import yellowc.app.allrank.data.remote.response.movie_response.BoxOfficeResult
import yellowc.app.allrank.data.remote.response.movie_response.WeeklyBoxOffice
import yellowc.app.allrank.di.DispatcherModule
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.models.ForeignMusicModel
import yellowc.app.allrank.domain.models.LibraryModel
import yellowc.app.allrank.domain.models.MyResult
import yellowc.app.allrank.domain.repositories.RetrofitRepositories
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @DispatcherModule.DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : RetrofitRepositories {
    override suspend fun getBookStoreResult(): List<BaseModel> {
        val result = ArrayList<BaseModel>()
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

                val temp = BaseModel(
                    title = book.title,
                    rank = book.rank.toString(),
                    img = book.coverLargeUrl,
                    owner = book.author,
                    content = book.description
                )

                result.add(temp)
            }


        }
        return result
    }

    override suspend fun getLibraryResult(start: String, end: String): List<BaseModel> {
        val result = ArrayList<BaseModel>()
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

                val temp = BaseModel(
                    rank = book.doc.ranking,
                    title = book.doc.bookname,
                    img = book.doc.bookImageURL,
                    owner = book.doc.authors,
                    content = book.doc.class_nm
                )

                result.add(temp)
            }
        }
        return result
    }

    override suspend fun getBoxOfficeResult(target: String): List<BaseModel> {
        val result = ArrayList<BaseModel>()
        withContext(dispatcherIO) {
            val responseList = async {
                remoteDataSource.getBoxOffice(target)
            }
            val boxOffice: List<WeeklyBoxOffice>

            when (val response = responseList.await()) {
                is MyResult.Success -> {
                    boxOffice = response.data.boxOfficeResult.weeklyBoxOfficeList
                }
                is MyResult.Error -> {
                    return@withContext
                }
            }
            for (movie in boxOffice) {

                val temp = BaseModel(
                    rank = movie.rank,
                    title = movie.movieNm,
                    img = "", //TODO 추가 api 연동!
                    owner = "누적 관객수: ${movie.audiAcc}",
                    content = "${movie.openDt} 개봉"
                )

                result.add(temp)
            }
        }
        return result
    }
}