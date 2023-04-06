package yellowc.app.allrank.ui.home.trend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import yellowc.app.allrank.AllRankApplication
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentTrendBinding
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.book.BookFragmentDirections
import yellowc.app.allrank.ui.home.HomeFragmentDirections
import yellowc.app.allrank.util.BOOK_DETAIL
import yellowc.app.allrank.util.JSOUP_TREND
import yellowc.app.allrank.util.NEWS_DETAIL
import yellowc.app.allrank.util.TREND_URL

@AndroidEntryPoint
class TrendFragment : BaseFragment<FragmentTrendBinding>(R.layout.fragment_trend) {
    private val viewModel: TrendViewModel by viewModels()
    private val adapter: BaseAdapter by lazy {
        BaseAdapter(
            itemClicked = {
                val action = HomeFragmentDirections.actionNavigationHomeToDetailFragment(
                    NEWS_DETAIL, it
                )
                requireView().findNavController().navigate(action)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visibility = View.VISIBLE
        viewModel.getSearched(TREND_URL, JSOUP_TREND)
        binding.trendRcv.adapter = adapter

        binding.refreshLayout.setColorSchemeColors(resources.getColor(R.color.main))
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getSearched(TREND_URL, JSOUP_TREND)
            binding.refreshLayout.isRefreshing = false
        }
        collectFlow()
    }




    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.keyword.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }


}