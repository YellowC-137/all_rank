package yellowc.app.allrank.ui.home

import android.content.ComponentName
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import yellowc.app.allrank.AllRankApplication
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.usecases.GetJsoupUseCase
import yellowc.app.allrank.util.AlarmManagers
import yellowc.app.allrank.util.BroadCastReceiver
import yellowc.app.allrank.util.JSOUP_TREND
import yellowc.app.allrank.util.TREND_URL
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getJsoupUseCase: GetJsoupUseCase
) : ViewModel() {
    private val _data = MutableStateFlow<List<BaseModel>>(emptyList())
    val data = _data


    fun getData(url: String, type: String) {
        viewModelScope.launch {
            _data.emit(getJsoupUseCase.invoke(url, type))
        }
    }

    suspend fun setAlarm() {
        getData(TREND_URL, JSOUP_TREND)
        val receiver =
            ComponentName(AllRankApplication.ApplicationContext(), BroadCastReceiver::class.java)

        AllRankApplication.ApplicationContext().packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
        var alarmData : String = ""
        data.collectLatest {
            if (it.isNotEmpty()) {
                for (i in it){
                    alarmData += "${i.rank} : ${i.title}\n"
                    Timber.e("TEST : DATA : $alarmData")
                }
            }
        }

        val am = AlarmManagers()
        am.test(AllRankApplication.ApplicationContext(),alarmData)
    }


}