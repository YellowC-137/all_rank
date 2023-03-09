package yellowc.app.allrank.domain.usecases

import yellowc.app.allrank.domain.repositories.JsoupRepositories
import javax.inject.Inject

class GetJsoupUseCase @Inject constructor(
    private val jsoupRepositories: JsoupRepositories) {
    suspend operator fun invoke(url: String,type:String) = jsoupRepositories.getJsoup(url,type)

}