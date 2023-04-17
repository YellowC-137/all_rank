package yellowc.app.allrank.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import yellowc.app.allrank.data.remote.response.game_search_response.GameSearchResponse
import yellowc.app.allrank.util.GAME_SEARCH_API_URL
import yellowc.app.allrank.BuildConfig.GAME_API_KEY


interface GameService {
    @GET(GAME_SEARCH_API_URL)//제목으로 게임의 상세정보 가져오기
    suspend fun getGameSearch(
        @Query("api_key") api_key:String= GAME_API_KEY,
        @Query("query") query: String,
        @Query("resources") resources:String = "game",
        @Query("format") format:String = "json"
    ):Response<GameSearchResponse>

}