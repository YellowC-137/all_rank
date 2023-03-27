package yellowc.app.allrank.ui.book.book_store

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentBookStoreBinding
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.book.BookFragmentDirections
import yellowc.app.allrank.util.BOOK_DETAIL

@AndroidEntryPoint
class BookStoreFragment : BaseFragment<FragmentBookStoreBinding>(R.layout.fragment_book_store) {
    private val viewModel: BookStoreViewModel by viewModels()
    private val adapter: BaseAdapter by lazy {
        BaseAdapter(
            itemClicked = {
                val action = BookFragmentDirections.actionNavigationBookToDetailFragment(
                    BOOK_DETAIL, it
                )
                requireView().findNavController().navigate(action)
            }
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visibility = View.VISIBLE
        viewModel.getBestSellers()
        binding.bookstoreRcv.adapter = adapter

        binding.refreshLayout.setColorSchemeColors(resources.getColor(R.color.main))
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getBestSellers()
            binding.refreshLayout.isRefreshing = false
        }

        collectFlow()
    }


    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bestsellers.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }

}