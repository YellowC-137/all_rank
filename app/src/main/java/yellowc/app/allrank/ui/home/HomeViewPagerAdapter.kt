package yellowc.app.allrank.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import yellowc.app.allrank.ui.home.news.NewsFragment
import yellowc.app.allrank.ui.home.trend.TrendFragment

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> TrendFragment()
            else-> NewsFragment()
        }
    }
}