package yellowc.app.allrank.ui.home.trend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.usecases.GetJsoupUseCase
import javax.inject.Inject

@HiltViewModel
class TrendViewModel @Inject constructor(
    private val getJsoupUseCase: GetJsoupUseCase
) : ViewModel() {
    private val _keywords = MutableStateFlow<List<BaseModel>>(emptyList())
    val keyword = _keywords


    fun getSearched(url:String,type:String) {
        viewModelScope.launch {
            _keywords.emit(getJsoupUseCase.invoke(url,type))
        }
    }

}