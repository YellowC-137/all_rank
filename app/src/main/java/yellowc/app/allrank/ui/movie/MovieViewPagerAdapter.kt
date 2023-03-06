package yellowc.app.allrank.ui.movie

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import yellowc.app.allrank.ui.movie.netflix.NetflixFragment
import yellowc.app.allrank.ui.movie.theater.TheaterFragment
import yellowc.app.allrank.ui.trend.news.NewsFragment
import yellowc.app.allrank.ui.trend.searched.SearchedFragment

class MovieViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> TheaterFragment()
            else-> NetflixFragment()
        }
    }
}