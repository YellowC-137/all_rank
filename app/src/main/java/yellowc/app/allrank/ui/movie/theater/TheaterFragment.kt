package yellowc.app.allrank.ui.movie.theater

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
import yellowc.app.allrank.databinding.FragmentTheaterBinding
import yellowc.app.allrank.domain.models.BaseModel
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.movie.MovieFragmentDirections
import yellowc.app.allrank.util.MOVIE_DETAIL

@AndroidEntryPoint
class TheaterFragment : BaseFragment<FragmentTheaterBinding>(R.layout.fragment_theater) {
    private val viewModel: TheaterViewModel by viewModels()
    private val adapter: BaseAdapter by lazy {
        BaseAdapter(
            itemClicked = {
                val action = MovieFragmentDirections.actionNavigationMovieToDetailFragment(
                    MOVIE_DETAIL, it
                )
                requireView().findNavController().navigate(action)
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
                        val tempList = arrayListOf<BaseModel>()
                        for (i in it){
                            val temp = BaseModel(
                                title = i.title,
                                img = i.img,
                                rank = i.rank,
                                content = i.director,
                                owner = i.pubDate,
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