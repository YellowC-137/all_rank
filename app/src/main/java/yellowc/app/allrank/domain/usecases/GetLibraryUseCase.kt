package yellowc.app.allrank.domain.usecases

import yellowc.app.allrank.domain.repositories.RetrofitRepositories
import javax.inject.Inject

class GetLibraryUseCase @Inject constructor(
    private val retrofitRepositories: RetrofitRepositories
) {
    suspend operator fun invoke(start: String, end: String) =
        retrofitRepositories.getLibraryResult(start, end)
}