package yellowc.app.allrank.ui.book.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.BookModel
import yellowc.app.allrank.domain.usecases.GetLibraryUseCase
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val getLibraryUseCase: GetLibraryUseCase
) : ViewModel() {
    private val _library = MutableStateFlow<List<BookModel>>(emptyList())
    val library = _library

    fun getlibrary(start: String, end: String) {
        viewModelScope.launch {
            _library.emit(getLibraryUseCase.invoke(start, end))
        }
    }
}