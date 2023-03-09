package yellowc.app.allrank.ui.music

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentMusicBinding
import yellowc.app.allrank.ui.base.BaseFragment

class MusicFragment : BaseFragment<FragmentMusicBinding>(R.layout.fragment_music) {
    private lateinit var viewpager: ViewPager2
    private lateinit var pagerAdapter: MusicViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = MusicViewPagerAdapter(this)
        viewpager = binding.musicViewpager2
        viewpager.adapter = pagerAdapter
        TabLayoutMediator(binding.musicTab, binding.musicViewpager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "국내 차트"
                }
                1 -> {
                    tab.text = "해외 차트"
                }
            }
        }.attach()
    }
}