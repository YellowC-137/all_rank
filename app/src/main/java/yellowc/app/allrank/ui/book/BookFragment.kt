package yellowc.app.allrank.ui.book

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentBookBinding
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.trend.TrendViewModel
import yellowc.app.allrank.ui.trend.TrendViewPagerAdapter

class BookFragment : BaseFragment<FragmentBookBinding>(R.layout.fragment_book) {
    private lateinit var viewpager : ViewPager2
    private val viewModel : BookViewModel by viewModels()
    private lateinit var pagerAdapter: BookViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
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