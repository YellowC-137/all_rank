package yellowc.app.allrank.data.remote.repositoryimpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import timber.log.Timber
import yellowc.app.allrank.data.remote.api.JsoupService
import yellowc.app.allrank.data.remote.response.jsoup_response.JsoupResponse
import yellowc.app.allrank.di.DispatcherModule
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.repositories.JsoupRepositories
import javax.inject.Inject

class JsoupRepositoryImpl @Inject constructor(
    private val jsoupMenuService: JsoupService,
    @DispatcherModule.DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : JsoupRepositories {
    override suspend fun getJsoup(url: String, type: String): List<BaseModel> {
        val result = ArrayList<BaseModel>()

        withContext(dispatcherIO) {
            val responseList = async {
                jsoupMenuService.startCrawling(url, type)
            }
            val items: List<JsoupResponse>
            val response = responseList.await()
            if (response.isNotEmpty()) {
                items = response
            } else {
                Timber.e("JSOUP NULL")
                return@withContext
            }

            for (item in items) {
                val temp = BaseModel(
                    title = item.title,
                    img = item.ImgUrl,
                    rank = item.rank,
                    content = item.description,
                    owner = item.owner
                )
                result.add(temp)
            }
        }
        return result

    }


}