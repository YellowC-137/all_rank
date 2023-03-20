package yellowc.app.allrank.ui.home.trend

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TrendAlarmManager(appContext:Context, workerParams: WorkerParameters):Worker(appContext,workerParams) {
    //API 26 or Higher
    override fun doWork(): Result {
        Noti()
        return Result.success()
    }

    private fun Noti() {
        TODO("Not yet implemented")
    }
}