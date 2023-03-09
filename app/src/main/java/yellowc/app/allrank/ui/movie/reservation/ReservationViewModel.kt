package yellowc.app.allrank.ui.movie.reservation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.usecases.GetJsoupUseCase
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val getJsoupUseCase: GetJsoupUseCase
) : ViewModel() {
    private val _reservation = MutableStateFlow<List<BaseModel>>(emptyList())
    val reservation = _reservation


    fun getReservation(url: String, type: String) {
        viewModelScope.launch {
            _reservation.emit(getJsoupUseCase.invoke(url, type))
        }
    }

}