package yellowc.app.allrank.ui.movie

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentMovieBinding
import yellowc.app.allrank.ui.base.BaseFragment

class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {
    private lateinit var viewpager: ViewPager2
    private lateinit var pagerAdapter: MovieViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = MovieViewPagerAdapter(this)
        viewpager = binding.movieViewpager2
        viewpager.adapter = pagerAdapter
        TabLayoutMediator(binding.movieTab, binding.movieViewpager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "박스오피스"
                }
                1 -> {
                    tab.text = "예매율"
                }
            }
        }.attach()
    }

}