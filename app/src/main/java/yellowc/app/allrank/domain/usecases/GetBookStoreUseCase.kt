package yellowc.app.allrank.domain.usecases

import yellowc.app.allrank.domain.repositories.RetrofitRepositories
import javax.inject.Inject

class GetBookStoreUseCase @Inject constructor(
    private val retrofitRepositories: RetrofitRepositories
) {
    suspend operator fun invoke() = retrofitRepositories.getBookStoreResult()
}