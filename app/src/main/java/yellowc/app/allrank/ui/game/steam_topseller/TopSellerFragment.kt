package yellowc.app.allrank.ui.game.steam_topseller

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
import yellowc.app.allrank.databinding.FragmentTopSellerBinding
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.music.korean.KoreanViewModel
import yellowc.app.allrank.util.JSOUP_KOREAN_MUSIC
import yellowc.app.allrank.util.JSOUP_STEAM_TOP_SELLER
import yellowc.app.allrank.util.MELON_CHART_URL
import yellowc.app.allrank.util.STEAM_TOP_SELLER_URL

@AndroidEntryPoint
class TopSellerFragment : BaseFragment<FragmentTopSellerBinding>(R.layout.fragment_top_seller) {


    private val viewModel:TopSellerViewModel by viewModels()
    private val adapter: BaseAdapter by lazy {
        BaseAdapter(
            itemClicked = {
                //TODO
            }
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopSeller(STEAM_TOP_SELLER_URL, JSOUP_STEAM_TOP_SELLER)
        binding.topsellerRcv.adapter = adapter
        collectFlow()
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topsellers.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                    }
                }
            }
        }
    }


}