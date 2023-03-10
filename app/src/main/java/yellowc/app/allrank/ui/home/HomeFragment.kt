package yellowc.app.allrank.ui.home

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentHomeBinding
import yellowc.app.allrank.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
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
    }

}