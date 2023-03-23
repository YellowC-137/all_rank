package yellowc.app.allrank.ui

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import yellowc.app.allrank.AllRankApplication
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.ActivityMainBinding
import yellowc.app.allrank.util.AlarmManagers
import yellowc.app.allrank.util.BroadCastReceiver

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)

        binding.navView.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )

        val receiver = ComponentName(this, BroadCastReceiver::class.java)

        this.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        getPermission()
        //setAlram()

    }

    private fun setAlram() {
        val context = AllRankApplication.ApplicationContext()
        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(context, BroadCastReceiver::class.java).let { intent ->
            intent.putExtra("data", "TEST")
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // FLAG_IMMUTABLE 추가
            )
        }


        val intervalMillis = 3000L
        val triggerTime = SystemClock.elapsedRealtime() + intervalMillis

        alarmManager.setRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerTime,
            intervalMillis,
            alarmIntent
        )
        Timber.e("TEST : ALRAM MANAGER")
    }

    private fun getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            TedPermission.create()
                .setPermissionListener(object : PermissionListener {
                    //권한이 허용됐을 때
                    override fun onPermissionGranted() {}
                    //권한이 거부됐을 때
                    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                        Snackbar.make(binding.root,"권한을 허용해주세요.",Snackbar.LENGTH_LONG).show()
                    }
                })
                .setDeniedMessage("권한을 허용해주세요.")// 권한이 없을 때 띄워주는 Dialog Message
                .setPermissions(Manifest.permission.POST_NOTIFICATIONS)// 얻으려는 권한(여러개 가능)
                .setPermissions(Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                .check()
        }
    }

}