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
import yellowc.app.allrank.domain.models.BookModel
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
    private lateinit var pplAdapter: RelatedAdapter
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(
            itemClicked = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("${it.link}")
                )
                requireContext().startActivity(intent)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.data = args.data
        pplAdapter = RelatedAdapter()
        viewModel.getVideo(args.data.title)


        //&#39 포함
        val title = arguments?.getString("title")
        val duration = arguments?.getInt("duration")
        //제목,제작자,날짜,장르,가격


        when (args.type) {
            BOOK_DETAIL -> {
                TODO("")
                val book = requireArguments().getSerializable("book") as BookModel
                Timber.e("TEST : ${book.title}")

                viewModel.getBook(args.data.owner!!)
                binding.apply {
                    rcvRelated.adapter = pplAdapter
                    rcvVideo.adapter = vidAdapter
                    tvRelatedTitle.text = "관련 도서"
                }
            }
            GAME_DETAIL -> {
                binding.apply {
                    rcvRelated.adapter = pplAdapter
                    rcvVideo.adapter = vidAdapter
                    tvRelatedTitle.text = "관련 게임"
                }
            }
            NEWS_DETAIL -> {
                //TREND
                viewModel.getNews(args.data.title)
                binding.apply {
                    rcvRelated.visibility = View.GONE
                    tvAuthor.visibility = View.GONE
                    tvContentTitle.visibility = View.GONE
                    tvContent.visibility = View.GONE
                    rcvTrendNews.visibility = View.VISIBLE
                    tvRelatedTitle.text = "관련 기사"
                }
            }
            MOVIE_DETAIL -> {
                binding.apply {
                    tvRelatedTitle.text = "감독의 다른 영화"
                }
            }
            MUSIC_DETAIL -> {
                binding.apply {
                    tvRelatedTitle.text = "가수의 다른 음악"
                }
            }
        }

        collectFlow()

    }

    private fun collectFlow() {
        //관련 정보
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.books.collectLatest {
                    if (it.isNotEmpty()) {
                        pplAdapter.submitList(it)
                    }
                }
            }
        }
        //유튜브 영상
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.video.collectLatest {
                    if (it.isNotEmpty()) {
                        vidAdapter.submitList(it)
                    }
                }
            }
        }
        //관련 뉴스
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.news.collectLatest {
                    if (it.isNotEmpty()) {

                    }
                }
            }
        }
        //관련 도서
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.books.collectLatest {
                    if (it.isNotEmpty()) {

                    }
                }
            }
        }
        //관련 게임
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.games.collectLatest {
                    if (it.isNotEmpty()) {

                    }
                }
            }
        }
        //가수의 다른 음악
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.music.collectLatest {
                    if (it.isNotEmpty()) {

                    }
                }
            }
        }
        //감독의 다른 영화
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movie.collectLatest {
                    if (it.isNotEmpty()) {

                    }
                }
            }
        }


    }
}