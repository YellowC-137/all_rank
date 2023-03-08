package yellowc.app.allrank.ui.movie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentMovieBinding
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.trend.TrendViewModel
import yellowc.app.allrank.ui.trend.TrendViewPagerAdapter

class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {
    private lateinit var viewpager : ViewPager2
    private val viewModel : MovieViewModel by viewModels()
    private lateinit var pagerAdapter: MovieViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = MovieViewPagerAdapter(this)
        viewpager = binding.movieViewpager2
        viewpager.adapter = pagerAdapter
        TabLayoutMediator(binding.movieTab,binding.movieViewpager2){
                tab,position ->
            when(position){
                0->{
                    tab.text = "영화 예매율"
                }
                1->{
                    tab.text = "넷플릭스"
                }
            }
        }.attach()
    }

}