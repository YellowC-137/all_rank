package yellowc.app.allrank.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock

class MyService : Service() {
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate() {
        super.onCreate()
        // AlarmManager 초기화
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        // PendingIntent 초기화
        val intent = Intent(this, BroadCastReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 주기적으로 jsoup 통신을 수행하는 코드
        val intervalMillis = 10 * 60 * 1000L // 10분
        val triggerTime = SystemClock.elapsedRealtime() + intervalMillis

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerTime,
            pendingIntent
        )

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        // Service 종료 시 AlarmManager와 PendingIntent 해제
        alarmManager.cancel(pendingIntent)
        pendingIntent.cancel()
        super.onDestroy()
    }
}