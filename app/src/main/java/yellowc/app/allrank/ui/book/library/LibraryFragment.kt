package yellowc.app.allrank.ui.book.library

import android.os.Bundle
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
import yellowc.app.allrank.AllRankApplication
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentLibraryBinding
import yellowc.app.allrank.domain.models.LibraryModel
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.detail.DetailFragment

@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>(R.layout.fragment_library) {
    private val viewModel: LibraryViewModel by viewModels()
    private val adapter: BaseAdapter by lazy {
        BaseAdapter(
            itemClicked = {
                    item ->
                val detailFragment = DetailFragment()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                //TODO transaction.replace(R.id.fragment_container, detailFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        )
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visibility = View.VISIBLE
        val end = AllRankApplication.getDay().first
        val start = AllRankApplication.getDay().second
        viewModel.getlibrary(start, end)
        binding.libraryRcv.adapter = adapter

        binding.refreshLayout.setColorSchemeColors( resources.getColor(R.color.main) )
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getlibrary(start, end)
            binding.refreshLayout.isRefreshing = false
        }
        collectFlow()

    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.library.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }

}