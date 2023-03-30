package yellowc.app.allrank.ui.movie.theater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.models.MovieModel
import yellowc.app.allrank.domain.usecases.GetBoxOfficeUseCase
import javax.inject.Inject

@HiltViewModel
class TheaterViewModel @Inject constructor(
    private val getBoxOfficeUseCase: GetBoxOfficeUseCase
) : ViewModel() {
    private val _boxoffice = MutableStateFlow<List<MovieModel>>(emptyList())
    val boxoffice = _boxoffice


    fun getBoxOffice(date: String) {
        viewModelScope.launch {
            _boxoffice.emit(getBoxOfficeUseCase.invoke(date))
        }
    }

}