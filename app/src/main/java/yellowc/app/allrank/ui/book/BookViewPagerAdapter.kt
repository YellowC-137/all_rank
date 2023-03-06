package yellowc.app.allrank.ui.book

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import yellowc.app.allrank.ui.book.book_store.BookStoreFragment
import yellowc.app.allrank.ui.book.library.LibraryFragment

class BookViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> BookStoreFragment()
            else-> LibraryFragment()
        }
    }
}