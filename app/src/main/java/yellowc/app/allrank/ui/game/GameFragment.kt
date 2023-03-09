package yellowc.app.allrank.ui.game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentGameBinding
import yellowc.app.allrank.ui.base.BaseFragment


class GameFragment : BaseFragment<FragmentGameBinding>(R.layout.fragment_game) {
    private lateinit var viewpager: ViewPager2
    private lateinit var pagerAdapter: GameViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = GameViewPagerAdapter(this)
        viewpager = binding.gameViewpager2
        viewpager.adapter = pagerAdapter
        TabLayoutMediator(binding.gameTab, binding.gameViewpager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "최고 인기"
                }
                1 -> {
                    tab.text = "최다 플레이"
                }
            }
        }.attach()
    }
}