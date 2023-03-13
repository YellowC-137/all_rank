package yellowc.app.allrank.ui.game.metacritic

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
import yellowc.app.allrank.databinding.FragmentMetacriticBinding
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.util.JSOUP_METACRITIC
import yellowc.app.allrank.util.METACRITIC_URL

@AndroidEntryPoint
class MetarciticFragment : BaseFragment<FragmentMetacriticBinding>(R.layout.fragment_metacritic) {


    private val viewModel: MetacriticViewModel by viewModels()
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
        viewModel.getTopSeller(METACRITIC_URL, JSOUP_METACRITIC)
        binding.metaRcv.adapter = adapter

        binding.refreshLayout.setColorSchemeColors( resources.getColor(R.color.main) )
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getTopSeller(METACRITIC_URL, JSOUP_METACRITIC)
            binding.refreshLayout.isRefreshing = false
        }
        collectFlow()
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topsellers.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }


}