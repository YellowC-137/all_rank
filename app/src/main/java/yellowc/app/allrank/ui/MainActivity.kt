package yellowc.app.allrank.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.ActivityMainBinding
import yellowc.app.allrank.util.AlarmManagers

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
        getPermission()
    }

    private fun getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            TedPermission.create()
                .setDeniedMessage("권한을 허용해주세요.")// 권한이 없을 때 띄워주는 Dialog Message
                .setPermissions(Manifest.permission.POST_NOTIFICATIONS)// 얻으려는 권한(여러개 가능)
                .setPermissions(Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                .setPermissionListener(object : PermissionListener {
                    //권한이 허용됐을 때
                    override fun onPermissionGranted() {
                        val alarm = AlarmManagers()
                        alarm.test()
                        Timber.e("ALRAM")
                    }

                    //권한이 거부됐을 때
                    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                        Snackbar.make(binding.root, "권한을 허용해주세요.", Snackbar.LENGTH_LONG).show()
                    }
                })
                .check()
        }
    }

}