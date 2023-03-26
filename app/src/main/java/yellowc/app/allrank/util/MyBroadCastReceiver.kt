package yellowc.app.allrank.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import timber.log.Timber

class MyBroadCastReceiver : BroadcastReceiver() {

    //Broadcast 수신시 자동 호출
    override fun onReceive(context: Context, intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Timber.e("FOREGROUND")
            context.startForegroundService(intent)
        } else {
            Timber.e("SERVICE")
            context.startService(intent)
        }
    }

}