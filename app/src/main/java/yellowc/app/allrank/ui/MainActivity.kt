package yellowc.app.allrank.ui

import android.Manifest
import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
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

        //getPermission()
        //initAlarm()

    }

    private fun initAlarm() {
        val receiver = ComponentName(AllRankApplication.instance.applicationContext, BroadCastReceiver::class.java)

        AllRankApplication.instance.applicationContext.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        val am =  AlarmManagers()
        am.setAlarm(AllRankApplication.ApplicationContext())

    }

}