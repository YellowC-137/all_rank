package yellowc.app.allrank.ui.music

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import yellowc.app.allrank.ui.music.billboard.BillboardFragment
import yellowc.app.allrank.ui.music.korean.KoreanFragment
import yellowc.app.allrank.ui.trend.news.NewsFragment
import yellowc.app.allrank.ui.trend.searched.SearchedFragment

class MusicViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> BillboardFragment()
            else-> KoreanFragment()
        }
    }
}