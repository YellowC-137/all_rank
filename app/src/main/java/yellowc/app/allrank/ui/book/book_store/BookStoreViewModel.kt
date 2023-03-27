package yellowc.app.allrank.ui.book.book_store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.usecases.GetBookStoreUseCase
import javax.inject.Inject

@HiltViewModel
class BookStoreViewModel @Inject constructor(
    private val getBookStoreUseCase: GetBookStoreUseCase
) : ViewModel() {
    private val _bestsellers = MutableStateFlow<List<BaseModel>>(emptyList())
    val bestsellers = _bestsellers


    fun getBestSellers() {
        viewModelScope.launch {
            _bestsellers.emit(getBookStoreUseCase.invoke())
        }
    }

}