package yellowc.app.allrank.ui.book

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import yellowc.app.allrank.ui.book.fiction.FictionFragment
import yellowc.app.allrank.ui.book.non_fiction.NonFictionFragment
import yellowc.app.allrank.ui.trend.news.NewsFragment
import yellowc.app.allrank.ui.trend.searched.SearchedFragment

class BookViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> FictionFragment()
            else-> NonFictionFragment()
        }
    }
}