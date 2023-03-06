package yellowc.app.allrank.ui.trend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentTrendBinding
import yellowc.app.allrank.ui.base.BaseFragment

class TrendFragment : BaseFragment<FragmentTrendBinding>(R.layout.fragment_trend) {
    private lateinit var viewpager : ViewPager2
    private val viewModel : TrendViewModel by viewModels()
    private lateinit var pagerAdapter: TrendViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = TrendViewPagerAdapter(this)
        viewpager = binding.trendViewpager2
        viewpager.adapter = pagerAdapter
        TabLayoutMediator(binding.trendTab,binding.trendViewpager2){
            tab,position ->
            when(position){
                0->{
                    tab.text = "실시간 검색어"
                }
                1->{
                    tab.text = "NEWS"
                }
            }
        }.attach()
    }

}