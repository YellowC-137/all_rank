package yellowc.app.allrank.ui.home.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentNewsBinding
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.util.JSOUP_NEWS
import yellowc.app.allrank.util.NEWS_URL

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(R.layout.fragment_news) {
    private val viewModel: NewsViewModel by viewModels()
    private val adapter: BaseAdapter by lazy {
        BaseAdapter(
            itemClicked = {
                //TODO
            }
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews(NEWS_URL, JSOUP_NEWS)
        binding.newsRcv.adapter = adapter
        collectFlow()
    }


    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.news.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                    }
                }
            }
        }
    }


}