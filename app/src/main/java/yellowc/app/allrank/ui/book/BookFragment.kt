package yellowc.app.allrank.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentBookBinding
import yellowc.app.allrank.ui.base.BaseFragment
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

class BookFragment : BaseFragment<FragmentBookBinding>(R.layout.fragment_book) {
    private lateinit var viewpager : ViewPager2
    private lateinit var pagerAdapter: BookViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = BookViewPagerAdapter(this)
        viewpager = binding.bookViewpager2
        viewpager.adapter = pagerAdapter
        TabLayoutMediator(binding.bookTab,binding.bookViewpager2){
                tab,position ->
            when(position){
                0->{
                    tab.text = "서점 판매"
                }
                1->{
                    tab.text = "도서관 대출"
                }
            }
        }.attach()
    }

}