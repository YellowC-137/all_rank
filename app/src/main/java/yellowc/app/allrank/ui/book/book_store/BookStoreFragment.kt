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
import timber.log.Timber
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentBookStoreBinding
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.domain.models.BookModel
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.book.BookFragmentDirections
import yellowc.app.allrank.ui.music.MusicFragmentDirections
import yellowc.app.allrank.util.BOOK_DETAIL

@AndroidEntryPoint
class BookStoreFragment : BaseFragment<FragmentBookStoreBinding>(R.layout.fragment_book_store) {
    private val viewModel: BookStoreViewModel by viewModels()
    private lateinit var bookModels: List<BookModel>
    private val adapter: BaseAdapter by lazy {
        BaseAdapter(
            itemClicked = {
                val action = BookFragmentDirections.actionNavigationBookToDetailFragment(
                    BOOK_DETAIL, it
                )
                TODO("")
                Timber.e("TEST : ${it.rank}")
                val bookModel = bookModels[it.rank.toInt()-1]
                Timber.e("TEST : ${bookModel.title}")
                action.arguments.putSerializable("book", bookModel)

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
                        bookModels = it
                        val tempList = arrayListOf<BaseModel>()
                        for (i in it) {
                            val temp = BaseModel(
                                title = i.title,
                                img = i.img,
                                rank = i.rank,
                                content = i.description,
                                owner = i.author,
                                link = ""
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
