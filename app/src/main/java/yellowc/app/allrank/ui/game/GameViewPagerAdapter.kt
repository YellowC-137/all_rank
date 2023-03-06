package yellowc.app.allrank.ui.game

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import yellowc.app.allrank.ui.game.metacritic.MetacriticFragment
import yellowc.app.allrank.ui.game.steam.SteamFragment
import yellowc.app.allrank.ui.trend.news.NewsFragment
import yellowc.app.allrank.ui.trend.searched.SearchedFragment

class GameViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> MetacriticFragment()
            else-> SteamFragment()
        }
    }
}