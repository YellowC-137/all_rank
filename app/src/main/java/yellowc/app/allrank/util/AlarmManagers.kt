package yellowc.app.allrank.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import timber.log.Timber
import yellowc.app.allrank.AllRankApplication
import java.util.*

class AlarmManagers {
    private var alarmMgr: AlarmManager? = null
    val context = AllRankApplication.ApplicationContext()

    fun setAlarm() {
        alarmMgr = AllRankApplication.ApplicationContext()
            .getSystemService(ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(context, MyBroadCastReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // FLAG_IMMUTABLE 추가
            )
        }

        // 오후 6시에 알람 설정
        /*TODO
        알람이 설정된 시간이 현재 시간보다 이전이면, 알람 매니저가 즉시 작동합니다.
현재 시간이 오전 9시인 경우, calendar 객체가 6시로 설정된 후 setRepeating() 메소드가 호출되면,
 calendar.timeInMillis는 이전 날짜로 설정되므로 즉시 알람이 실행
         */
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 18)
        }

        // 매일 하루에 한번씩 반복함.
        alarmMgr!!.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )
    }

    fun cancelAlarm(intent: Intent, requestCode: Int) {
        val context = AllRankApplication.ApplicationContext()
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmMgr!!.cancel(pendingIntent)
    }

}