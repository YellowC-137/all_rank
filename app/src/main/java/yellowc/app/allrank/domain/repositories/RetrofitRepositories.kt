package yellowc.app.allrank.domain.repositories

import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.models.ForeignMusicModel

interface RetrofitRepositories {

    suspend fun getBookStoreResult(): List<BaseModel>
    suspend fun getLibraryResult(start: String, end: String): List<BaseModel>
}