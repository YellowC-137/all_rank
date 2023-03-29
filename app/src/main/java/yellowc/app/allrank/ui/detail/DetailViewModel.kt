package yellowc.app.allrank.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import yellowc.app.allrank.domain.models.*
import yellowc.app.allrank.domain.usecases.GetBookSearchUseCase
import yellowc.app.allrank.domain.usecases.GetNewsSearchUseCase
import yellowc.app.allrank.domain.usecases.GetVideoUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getVideoUseCase: GetVideoUseCase,
    private val getBookSearchUseCase: GetBookSearchUseCase,
    private val getNewsSearchUseCase: GetNewsSearchUseCase
) : ViewModel() {
    private val _video = MutableStateFlow<List<Videos>>(emptyList())
    val video = _video

    private val _actors = MutableStateFlow<List<MovieModel>>(emptyList())
    val actors = _actors
    //영화의 경우 출연 배우

    private val _books = MutableStateFlow<List<BookModel>>(emptyList())
    val books = _books
    //도서의 경우 관련 도서

    private val _games = MutableStateFlow<List<GameModel>>(emptyList())
    val games = _games
    //게임의 경우 관련 게임

    private val _music = MutableStateFlow<List<MusicModel>>(emptyList())
    val music = _music
    //음악의 경우 아티스트의 다른 음악

    fun getVideo(query: String) {
        viewModelScope.launch {
            _video.emit(getVideoUseCase.invoke(query))
        }
    }

    fun getBook(query: String) {
        viewModelScope.launch {
            _books.emit(getBookSearchUseCase.invoke(query))
        }
    }

}