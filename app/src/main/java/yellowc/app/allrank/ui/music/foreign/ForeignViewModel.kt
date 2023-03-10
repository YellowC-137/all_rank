package yellowc.app.allrank.ui.music.foreign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.usecases.GetJsoupUseCase
import javax.inject.Inject

@HiltViewModel
class ForeignViewModel  @Inject constructor(
    private val getJsoupUseCase: GetJsoupUseCase
) : ViewModel() {
    private val _billboard = MutableStateFlow<List<BaseModel>>(emptyList())
    val billboard = _billboard


    fun getForeign(url: String, type: String) {
        viewModelScope.launch {
            _billboard.emit(getJsoupUseCase.invoke(url, type))
        }
    }

}