package yellowc.app.allrank.util

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import yellowc.app.allrank.data.remote.api.JsoupService
import java.util.*

@SuppressLint("ServiceCast")
class AlarmManagers {

    /*
    TODO
    1. 권한 설정
    2. service나 async로 background로 jsoup 통신 사용하게 설정
    3. 테스트 -> 그냥 실행시에 바로 띄우기 
    * */
    

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    fun setAlarm(context:Context) {
        val intent = Intent(context, JsoupService::class.java) // usecase?
        val pendingIntent = PendingIntent.getService(context, 0, intent, 0)

        // 오후 6시에 알람 설정
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 18)
        }

        // 매일 하루에 한번씩 반복함.
        alarmMgr?.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )

    }

}