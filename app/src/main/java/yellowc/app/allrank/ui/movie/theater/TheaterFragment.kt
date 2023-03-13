package yellowc.app.allrank.ui.movie.theater

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import yellowc.app.allrank.AllRankApplication
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentTheaterBinding
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment

@AndroidEntryPoint
class TheaterFragment : BaseFragment<FragmentTheaterBinding>(R.layout.fragment_theater) {
    private val viewModel: TheaterViewModel by viewModels()
    private val adapter: BaseAdapter by lazy {
        BaseAdapter(
            itemClicked = {
                //TODO
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visibility = View.VISIBLE
        val target = AllRankApplication.getDay().third
        viewModel.getBoxOffice(target)
        binding.theaterRcv.adapter = adapter

        binding.refreshLayout.setColorSchemeColors( resources.getColor(R.color.main) )
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getBoxOffice(target)
            binding.refreshLayout.isRefreshing = false
        }
        collectFlow()
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.boxoffice.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }

}