package yellowc.app.allrank.ui

import android.Manifest
import android.content.Intent
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
import yellowc.app.allrank.AllRankApplication
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

        // 프래그먼트 백 스택에 있는 경우 뒤로 가기 버튼 누르면 이전 프래그먼트로 이동
        supportFragmentManager.addOnBackStackChangedListener {
            val count = supportFragmentManager.backStackEntryCount
            if (count == 0) {
                finish()
            }
        }

        getPermission()
    }

override fun onBackPressed() {
    if (supportFragmentManager.backStackEntryCount > 0) {
        supportFragmentManager.popBackStack()
    } else {
        super.onBackPressed()
    }
}

    private fun getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            TedPermission.create()
                .setPermissions(Manifest.permission.POST_NOTIFICATIONS)// 얻으려는 권한(여러개 가능)
                .setPermissionListener(object : PermissionListener {
                    //권한이 허용됐을 때
                    override fun onPermissionGranted() {
                        val alarm = AlarmManagers()
                        //alarm.test()
                        Timber.e("ALRAM")
                        alarm.setAlarm()
                    }

                    //권한이 거부됐을 때
                    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                        Snackbar.make(binding.root, "권한을 허용해주세요.", Snackbar.LENGTH_LONG).show()
                    }
                })
                .setDeniedMessage("권한을 허용해주세요.")// 권한이 없을 때 띄워주는 Dialog Message
                .check()
        }
    }

}