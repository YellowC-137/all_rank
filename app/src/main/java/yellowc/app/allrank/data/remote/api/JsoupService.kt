package yellowc.app.allrank.data.remote.api

import yellowc.app.allrank.data.remote.response.jsoup_response.JsoupResponse

interface JsoupService {
    suspend fun startCrawling(url: String,type:String): List<JsoupResponse>
}