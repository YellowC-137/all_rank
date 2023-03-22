package yellowc.app.allrank.ui.home

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import yellowc.app.allrank.AllRankApplication
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentHomeBinding
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.util.AlarmManagers
import yellowc.app.allrank.util.BroadCastReceiver

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var viewpager: ViewPager2
    private lateinit var pagerAdapter: HomeViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = HomeViewPagerAdapter(this)
        viewpager = binding.homeViewpager2
        viewpager.adapter = pagerAdapter
        TabLayoutMediator(binding.homeTab, binding.homeViewpager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "실시간 검색어"
                }
                1 -> {
                    tab.text = "주요 뉴스"
                }
            }
        }.attach()
        initAlarm()
        collectFlow()


    }


    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.news.collectLatest {
                    if (it.isNotEmpty()) {

                    }
                }
            }
        }
    }

    private fun initAlarm() {
        val receiver =
            ComponentName(AllRankApplication.ApplicationContext(), BroadCastReceiver::class.java)

        AllRankApplication.ApplicationContext().packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        val am = AlarmManagers()
        am.setAlarm(AllRankApplication.ApplicationContext())
    }

}