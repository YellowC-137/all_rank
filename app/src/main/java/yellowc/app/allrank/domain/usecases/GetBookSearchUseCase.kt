package yellowc.app.allrank.domain.usecases

import yellowc.app.allrank.domain.repositories.RetrofitRepositories
import javax.inject.Inject

class GetBookSearchUseCase @Inject constructor(
    private val retrofitRepositories: RetrofitRepositories
) {
    suspend operator fun invoke(query: String) =
        retrofitRepositories.getBookSearchResult(query)
}