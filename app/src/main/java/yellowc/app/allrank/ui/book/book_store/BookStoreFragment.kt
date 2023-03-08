package yellowc.app.allrank.ui.book.book_store

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
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentBookStoreBinding
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
@AndroidEntryPoint
class BookStoreFragment : BaseFragment<FragmentBookStoreBinding>(R.layout.fragment_book_store) {
    private val viewModel: BookStoreViewModel by viewModels()
    private val adapter: BaseAdapter by lazy {
        BaseAdapter(
            itemClicked = {
                //TODO
            }
        )
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bestsellers.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBestSellers()
        binding.bookstoreRcv.adapter = adapter
        collectFlow()
    }

}