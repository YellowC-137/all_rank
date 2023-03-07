package yellowc.app.allrank.data.remote.api

interface JsoupService {
    suspend fun startCrawling(target:String)
    //TODO 각 기능별 분리 , deatil view에서 사용?
}