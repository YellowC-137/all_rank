package yellowc.app.allrank.ui.home.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.usecases.GetJsoupUseCase
import javax.inject.Inject

@HiltViewModel
class NewsViewModel  @Inject constructor(
    private val getJsoupUseCase: GetJsoupUseCase
) : ViewModel() {
    private val _news = MutableStateFlow<List<BaseModel>>(emptyList())
    val news = _news


    fun getNews(url:String,type:String) {
        viewModelScope.launch {
            _news.emit(getJsoupUseCase.invoke(url,type))
        }
    }

}