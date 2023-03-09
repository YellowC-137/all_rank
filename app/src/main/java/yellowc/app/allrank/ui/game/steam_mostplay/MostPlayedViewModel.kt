package yellowc.app.allrank.ui.game.steam_mostplay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.usecases.GetJsoupUseCase
import javax.inject.Inject

@HiltViewModel
class MostPlayedViewModel  @Inject constructor(
    private val getJsoupUseCase: GetJsoupUseCase
) : ViewModel() {
    private val _mostplay = MutableStateFlow<List<BaseModel>>(emptyList())
    val mostplay = _mostplay


    fun getMostPlay(url: String, type: String) {
        viewModelScope.launch {
            _mostplay.emit(getJsoupUseCase.invoke(url, type))
        }
    }

}