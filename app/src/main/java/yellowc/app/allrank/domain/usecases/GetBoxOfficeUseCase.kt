package yellowc.app.allrank.domain.usecases

import yellowc.app.allrank.domain.repositories.RetrofitRepositories
import javax.inject.Inject

class GetBoxOfficeUseCase@Inject constructor(
    private val retrofitRepositories: RetrofitRepositories
) {
    suspend operator fun invoke(target:String) = retrofitRepositories.getBoxOfficeResult(target)
}