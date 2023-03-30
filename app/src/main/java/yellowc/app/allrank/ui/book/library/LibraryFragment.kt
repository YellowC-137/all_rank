package yellowc.app.allrank.ui.book.library

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
import yellowc.app.allrank.AllRankApplication
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentLibraryBinding
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.book.BookFragmentDirections
import yellowc.app.allrank.ui.detail.DetailFragment
import yellowc.app.allrank.util.BOOK_DETAIL

@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>(R.layout.fragment_library) {
    private val viewModel: LibraryViewModel by viewModels()
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
                        val tempList = arrayListOf<BaseModel>()
                        for (i in it){
                            val temp = BaseModel(
                                title = i.title,
                                img = i.img,
                                rank = i.rank,
                                content = i.genre,
                                owner = i.author,
                                link = i.link
                            )
                            tempList.add(temp)
                        }
                        adapter.submitList(tempList)
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }

}