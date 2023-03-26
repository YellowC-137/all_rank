package yellowc.app.allrank.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.Videos
import yellowc.app.allrank.domain.usecases.GetVideoUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getVideoUseCase: GetVideoUseCase
) : ViewModel() {
    private val _video = MutableStateFlow<List<Videos>>(emptyList())
    val video = _video


    fun getvideo(query: String) {
        viewModelScope.launch {
            _video.emit(getVideoUseCase.invoke(query))
        }
    }

    fun getData() {}

    fun getPeople() {}
}