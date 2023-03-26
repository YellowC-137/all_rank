package yellowc.app.allrank

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@HiltAndroidApp
class AllRankApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        instance = this

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