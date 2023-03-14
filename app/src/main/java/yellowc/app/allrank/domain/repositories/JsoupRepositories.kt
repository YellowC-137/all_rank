package yellowc.app.allrank.domain.repositories

import yellowc.app.allrank.domain.models.BaseModel

interface JsoupRepositories {
    suspend fun getJsoup(url:String,type:String): List<BaseModel>

}