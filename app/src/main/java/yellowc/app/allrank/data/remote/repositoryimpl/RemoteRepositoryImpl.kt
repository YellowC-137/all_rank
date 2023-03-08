package yellowc.app.allrank.data.remote.repositoryimpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import yellowc.app.allrank.data.remote.datasource.RemoteDataSource
import yellowc.app.allrank.data.remote.response.bookstore_response.Item
import yellowc.app.allrank.data.remote.response.foreign_response.Track
import yellowc.app.allrank.data.remote.response.library_response.Doc
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

                //val temp = BookStoreModel(
                //                    title = book.title,
                //                    rank = book.rank,
                //                    description = book.description,
                //                    pubDate = book.pubDate,
                //                    publisher = book.publisher,
                //                    imgUrl = book.coverLargeUrl,
                //                    author = book.author
                //                )

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

    //TODO FIX 이미지 처리
    override suspend fun getForeignMusicResult(): List<ForeignMusicModel> {
        val result = ArrayList<ForeignMusicModel>()
        withContext(dispatcherIO) {
            val responseList = async {
                remoteDataSource.getForeign()
            }
            val musics: List<Track>

            when (val response = responseList.await()) {
                is MyResult.Success -> {
                    musics = response.data.tracks.track
                }
                is MyResult.Error -> {
                    return@withContext
                }
            }
            for (foreign in musics) {
                val temp = ForeignMusicModel(
                    rank = musics.indexOf(foreign),
                    artist_name = foreign.artist.name,
                    track_name = foreign.name,
                    ImgUrl = ""
                )
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

                //val temp = LibraryModel(
                //                    rank = book.doc.ranking,
                //                    publisher = book.doc.publisher,
                //                    publication_year = book.doc.publication_year,
                //                    bookname = book.doc.bookname,
                //                    bookImageURL = book.doc.bookImageURL,
                //                    authors = book.doc.authors,
                //                    class_nm = book.doc.class_nm
                //                )

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
}