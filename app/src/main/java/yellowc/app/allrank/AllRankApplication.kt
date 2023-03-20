package yellowc.app.allrank

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import yellowc.app.allrank.ui.home.trend.TrendAlarmManager
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@HiltAndroidApp
class AllRankApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        initAlarmManager()
        instance = this
    }

    private fun initAlarmManager() {
        val alarmManager =
            ApplicationContext().getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val pendingIntent =
            PendingIntent.getService(ApplicationContext(), 0, Intent(),
                PendingIntent.FLAG_NO_CREATE)
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent)
        }

    }

    companion object {
        lateinit var instance: AllRankApplication

        fun ApplicationContext(): Context {
            return instance.applicationContext
        }

        fun getDay(): Triple<String, String, String> {
            val currentDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val theater_form = DateTimeFormatter.ofPattern("yyyyMMdd")
            val today = currentDate.format(formatter)
            val weekAgoRaw = currentDate.minusWeeks(1)
            val weekAgo = weekAgoRaw.format(formatter)
            val target = weekAgoRaw.format(theater_form)

            return Triple(today, weekAgo, target)
        }
    }
}