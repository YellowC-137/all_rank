package yellowc.app.allrank.ui.game.metacritic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.usecases.GetJsoupUseCase
import javax.inject.Inject

@HiltViewModel
class MetacriticViewModel @Inject constructor(
    private val getJsoupUseCase: GetJsoupUseCase
) : ViewModel() {
    private val _topsellers = MutableStateFlow<List<BaseModel>>(emptyList())
    val topsellers = _topsellers


    fun getTopSeller(url: String, type: String) {
        viewModelScope.launch {
            _topsellers.emit(getJsoupUseCase.invoke(url, type))
        }
    }

}