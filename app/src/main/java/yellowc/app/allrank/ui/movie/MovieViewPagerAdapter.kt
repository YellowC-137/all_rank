package yellowc.app.allrank.ui.movie

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import yellowc.app.allrank.ui.movie.reservation.ReservationFragment
import yellowc.app.allrank.ui.movie.theater.TheaterFragment

class MovieViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> TheaterFragment()
            else-> ReservationFragment()
        }
    }
}