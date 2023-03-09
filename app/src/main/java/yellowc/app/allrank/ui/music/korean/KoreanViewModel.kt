package yellowc.app.allrank.ui.music.korean

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.usecases.GetJsoupUseCase
import javax.inject.Inject

@HiltViewModel
class KoreanViewModel @Inject constructor(
    private val getJsoupUseCase: GetJsoupUseCase
) : ViewModel() {
    private val _meloncharts = MutableStateFlow<List<BaseModel>>(emptyList())
    val melon = _meloncharts


    fun getMelon(url: String, type: String) {
        viewModelScope.launch {
            _meloncharts.emit(getJsoupUseCase.invoke(url, type))
        }
    }

}