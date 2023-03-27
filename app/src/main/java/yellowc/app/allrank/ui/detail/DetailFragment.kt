package yellowc.app.allrank.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentDetailBinding
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.book.BookFragmentDirections
import yellowc.app.allrank.util.*

@AndroidEntryPoint
class DetailFragment() : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel: DetailViewModel by viewModels()
    val args: DetailFragmentArgs by navArgs()
    private val vidAdapter: VideoAdapter by lazy {
        VideoAdapter(
            itemClicked = {
          //TODO 유튜브 영상 재생 , video box clip
            }
        )
    }
    private lateinit var pplAdapter: PeopleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visibility = View.VISIBLE
        binding.data = args.data
        pplAdapter = PeopleAdapter()
        viewModel.getvideo(args.data.title)

        when (args.type) {
            BOOK_DETAIL -> {
                binding.apply {
                    tvRate.visibility = View.GONE
                    tvGenre.visibility = View.GONE
                    tvPeopleTitle.text = "작가의 다른 도서"
                    rcvPeople.adapter = pplAdapter
                    rcvVideo.adapter = vidAdapter
                }
            }
            GAME_DETAIL -> {
                binding.apply {
//TODO
                }
            }
            NEWS_DETAIL -> {
                binding.apply {
//TODO
                }
            }
            MOVIE_DETAIL -> {
                binding.apply {
                    tvRate.visibility = View.GONE
                    tvPeopleTitle.text = "출연 배우"
                }
            }
            MUSIC_DETAIL -> {
                binding.apply {
//TODO
                }
            }

        }




        collectFlow()

    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.video.collectLatest {
                    if (it.isNotEmpty()) {
                        vidAdapter.submitList(it)
                        binding.progressbar.visibility = View.GONE
                    }
                }

                //TODO viewModel.people.collectLatest

            }
        }
    }
}