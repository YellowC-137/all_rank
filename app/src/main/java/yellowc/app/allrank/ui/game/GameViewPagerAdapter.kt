package yellowc.app.allrank.ui.game

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import yellowc.app.allrank.ui.game.steam_mostplay.MostPlayedFragment
import yellowc.app.allrank.ui.game.steam_topseller.TopSellerFragment

class GameViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> TopSellerFragment()
            else-> MostPlayedFragment()
        }
    }
}