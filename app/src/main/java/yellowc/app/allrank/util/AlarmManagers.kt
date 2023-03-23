package yellowc.app.allrank.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import timber.log.Timber
import yellowc.app.allrank.AllRankApplication
import java.util.*

class AlarmManagers {
    private var alarmMgr: AlarmManager? = null

    fun setAlarm(message: String) {
        val context = AllRankApplication.ApplicationContext()
        alarmMgr = AllRankApplication.ApplicationContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(context, BroadCastReceiver::class.java).let { intent ->
            intent.putExtra("data", message)
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // FLAG_IMMUTABLE 추가
            )
        }

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

    fun test(message: String) {
        val context = AllRankApplication.ApplicationContext()
        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, BroadCastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // FLAG_IMMUTABLE 추가
        )
        val intervalMillis = 90 * 1000 // 10분
        val triggerTime = SystemClock.elapsedRealtime() + intervalMillis

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,
                pendingIntent
            )
        } else {
            alarmManager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,
                pendingIntent
            )
        }
        Timber.e("TEST : ALRAM MANAGER")
    }

}