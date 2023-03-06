package yellowc.app.allrank

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

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
    }
}