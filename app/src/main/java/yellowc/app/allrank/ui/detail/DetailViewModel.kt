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

    private val _movie = MutableStateFlow<List<MovieModel>>(emptyList())
    val movie = _movie
    //영화의 경우 관련 영화

    private val _books = MutableStateFlow<List<RelateModel>>(emptyList())
    val books = _books
    //도서의 경우 관련 도서

    private val _games = MutableStateFlow<List<GameModel>>(emptyList())
    val games = _games
    //게임의 경우 관련 게임

    private val _music = MutableStateFlow<List<MusicModel>>(emptyList())
    val music = _music
    //음악의 경우 아티스트의 다른 음악

    private val _news = MutableStateFlow<List<BaseModel>>(emptyList())
    val news = _news

    fun getVideo(query: String) {
        viewModelScope.launch {
            _video.emit(getVideoUseCase.invoke(query))
        }
    }

    fun getBook(query: String) {
        viewModelScope.launch {
            val temp = getBookSearchUseCase.invoke(query)
            val ppl = arrayListOf<RelateModel>()
            for (i in temp) {
                ppl.add(
                    RelateModel(
                        img = i.img!!,
                        name = i.title,
                        age = "",
                        role = ""
                    )
                )
                Timber.e(i.title)
            }
            _books.emit(ppl)
        }
    }

    fun getNews(query: String) {
        viewModelScope.launch {
            _news.emit(getNewsSearchUseCase.invoke(query))
        }
    }


//음악  음악정보 따로 가사 따로 , 가사는 없을수도 있음!
    fun getLyrics(song: String){}
//가수의 다른 음악
    fun getRelateMusic(artist: String){}

    //감독의 다른 영화
    fun getRelateMovie(director: String){}

    //게임 정보, 게임 id로 가져와야함
    fun getGame(game: String){}

    //DLC나 관련 게임
    fun getDLCs(game: String){}//?



}