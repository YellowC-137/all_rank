package yellowc.app.allrank.ui.music.foreign

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentForeignBinding
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.music.korean.KoreanViewModel
import yellowc.app.allrank.util.FOREIGN_MUSIC_URL
import yellowc.app.allrank.util.JSOUP_FOREIGN
import yellowc.app.allrank.util.JSOUP_KOREAN_MUSIC
import yellowc.app.allrank.util.MELON_CHART_URL

@AndroidEntryPoint
class ForeignFragment : BaseFragment<FragmentForeignBinding>(R.layout.fragment_foreign) {
    private val viewModel: ForeignViewModel by viewModels()
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
        viewModel.getForeign(FOREIGN_MUSIC_URL, JSOUP_FOREIGN)
        binding.foreignRcv.adapter = adapter
        binding.refreshLayout.setColorSchemeColors( resources.getColor(R.color.main) )
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getForeign(FOREIGN_MUSIC_URL, JSOUP_FOREIGN)
            binding.refreshLayout.isRefreshing = false
        }

        collectFlow()
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.billboard.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }


}