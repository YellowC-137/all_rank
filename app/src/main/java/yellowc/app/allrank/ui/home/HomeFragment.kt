package yellowc.app.allrank.ui.home

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
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
import timber.log.Timber
import yellowc.app.allrank.AllRankApplication
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentHomeBinding
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var viewpager: ViewPager2
    private lateinit var pagerAdapter: HomeViewPagerAdapter
    private lateinit var am : AlarmManagers

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
        alram()
        collectFlow()


    }

    private fun alram() {
        viewModel.getData(TREND_URL, JSOUP_TREND)
        am = AlarmManagers()

        Timber.e("TEST : ALRAM MANAGER")
    }


    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collectLatest {
                    if (it.isNotEmpty()) {
                        var data = ""
                        for (i in it){
                            data += "${i.rank}. ${i.title}\n"
                        }
                        am.setAlarm(data)
                    }
                }
            }
        }
    }
}