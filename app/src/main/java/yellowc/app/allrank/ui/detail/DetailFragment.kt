package yellowc.app.allrank.ui.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentDetailBinding
import yellowc.app.allrank.domain.models.People
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.util.*

@AndroidEntryPoint
class DetailFragment() : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel: DetailViewModel by viewModels()
    val args: DetailFragmentArgs by navArgs()
    private val vidAdapter: VideoAdapter by lazy {
        VideoAdapter(
            itemClicked = {
                val firstIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:${it.videoId}"))
                val secondIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=${it.videoId}")
                )
                try {
                    requireContext().startActivity(firstIntent)
                } catch (e: ActivityNotFoundException) {
                    requireContext().startActivity(secondIntent)
                }

            }
        )
    }
    private lateinit var pplAdapter: PeopleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.data = args.data
        pplAdapter = PeopleAdapter()
        viewModel.getVideo(args.data.title)
        //&#39 포함

        when (args.type) {
            BOOK_DETAIL -> {
                binding.apply {
                    //TODO recyclerView 안보임! 수정
                    Timber.e("BOOK: ${args.data.owner}")
                    args.data.owner?.let { viewModel.getBook(it) }
                    rcvPeople.adapter = pplAdapter
                    rcvVideo.adapter = vidAdapter
                    tvRate.visibility = View.GONE
                    tvGenre.visibility = View.GONE
                    tvDate.visibility = View.GONE
                    tvPeopleTitle.text = "관련 도서"
                }
            }
            GAME_DETAIL -> {
                binding.apply {
//TODO
                    rcvPeople.adapter = pplAdapter
                    rcvVideo.adapter = vidAdapter
                    args.data.owner?.let { viewModel.getBook(it) }
                    tvRate.visibility = View.GONE
                    tvGenre.visibility = View.GONE
                    tvDate.visibility = View.GONE
                    tvPeopleTitle.text = "관련 게임"
                }
            }
            NEWS_DETAIL -> {
                binding.apply {
//TODO
                    rcvPeople.visibility = View.GONE
                    tvPeopleTitle.visibility = View.GONE


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
                    }
                }

                viewModel.books.collectLatest {
                    if (it.isNotEmpty()) {
                        val temp = ArrayList<People>()
                        for (i in it) {
                            temp.add(
                                People(
                                    img = i.img,
                                    name = i.title,
                                    age = null,
                                    role = null
                                )
                            )
                        }
                        Timber.e("BOOK: ${temp.size}")
                        //TODO 응답 데이터 수정
                        pplAdapter.submitList(temp)
                    }
                }

                //TODO viewModel.people.collectLatest

            }
        }
    }
}