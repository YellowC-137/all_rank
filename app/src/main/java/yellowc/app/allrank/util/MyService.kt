package yellowc.app.allrank.util

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import timber.log.Timber
import yellowc.app.allrank.AllRankApplication
import yellowc.app.allrank.R
import yellowc.app.allrank.data.remote.response.jsoup_response.JsoupResponse
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.repositories.JsoupRepositories
import yellowc.app.allrank.domain.usecases.GetJsoupUseCase
import yellowc.app.allrank.ui.MainActivity

class MyService : Service() {
    lateinit var notificationManager: NotificationManager
    val context = AllRankApplication.ApplicationContext()

    override fun onCreate() {
        super.onCreate()
        notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        createNotification()
        Timber.e("SERVICE START")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 주기적으로 jsoup 통신을 수행
        Timber.e("onSTARTCOMMAND")
        var data = ""

        CoroutineScope(Dispatchers.IO).launch {
            val result = async { Jsoup() }.await()
            for (i in result){
                data+= "${i.rank}. ${i.title}\n"
            }
            val noti : Notification = buildNotification(context, data)
            startForeground(NOTIFICATION_ID, noti)
            notificationManager.notify(NOTIFICATION_ID, noti)
            Timber.e("TEST :"+data)
        }



        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }



    private fun createNotification() {
        Timber.e("TEST : CREATEBROADCAST")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true) // 불빛
            notificationChannel.lightColor = Color.YELLOW // 색상
            notificationChannel.enableVibration(true)//진동
            notificationChannel.description = NOTIFICATION_CHANNEL_DESCRIPTION // 채널 정보
            notificationManager.createNotificationChannel(
                notificationChannel
            )

        }
    }


    private fun buildNotification(context: Context, message: String): Notification {
        Timber.e("build TEST :"+message)
        val contentIntent = Intent(context, MainActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID, // requestCode
            contentIntent, // 알림 클릭 시 이동할 인텐트
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round) // 아이콘
            .setContentTitle("현재 실시간 검색어를 확인해 보세요!") // 제목
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        val bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.setBigContentTitle("현재 실시간 검색어를 확인해 보세요!") // 펼쳐졌을 때의 제목
        bigTextStyle.bigText(message) // 펼쳐졌을 때의 내용
        builder.setStyle(bigTextStyle)


        Timber.e("TEST : SENDBROADCAST")

        return builder.build()
    }

    private fun Jsoup(): ArrayList<BaseModel> {

            val result = ArrayList<BaseModel>()
            val doc: Document = Jsoup.connect(TREND_URL).get()
            val div = doc.body().select("div.col-sm-12")
            val table = div.select("table.table-hover.table-striped").first()
            val trs = table!!.select("tr:not([class])")
            for (tr in trs) {
                val rank = tr.select("span.realtimeKeyRank").text()
                val td = tr.select("td.ellipsis100")
                val title = td.select("a").attr("title")
                val temp = BaseModel(
                    rank = rank,
                    title = title,
                    owner = "",
                    img = "",
                    content = "",
                    link=""
                )
                result.add(temp)
            }

            return result
    }
}