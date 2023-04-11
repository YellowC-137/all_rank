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
    private lateinit var relatedAdapter: RelatedAdapter
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
        relatedAdapter = RelatedAdapter()
        viewModel.getVideo(args.data.title)
        Timber.e("type : ${args.type}, title: ${args.data.title} , owner: ${args.data.owner}")
        collectVideo()

        when (args.type) {
            BOOK_DETAIL -> {
                viewModel.getBook(args.data.owner!!)
                binding.apply {
                    rcvRelated.adapter = relatedAdapter
                    rcvVideo.adapter = vidAdapter
                    tvRelatedTitle.text = "관련 도서"
                    tvContentTitle.text = "줄거리"
                }
                collectBook()
            }
            GAME_DETAIL -> {
                binding.apply {
                    rcvRelated.adapter = relatedAdapter
                    rcvVideo.adapter = vidAdapter
                    tvRelatedTitle.text = "관련 게임"
                }
                collectGame()
            }
            NEWS_DETAIL -> {
                // <b> ,</b>,&apos,&quot
                viewModel.getNews(args.data.title)
                binding.apply {
                    border.visibility = View.GONE
                    rcvTrendNews.adapter = newsAdapter
                    rcvRelated.visibility = View.GONE
                    tvAuthor.visibility = View.GONE
                    tvContentTitle.visibility = View.GONE
                    tvContent.visibility = View.GONE
                    rcvTrendNews.visibility = View.VISIBLE
                    tvRelatedTitle.text = "관련 기사"
                }
                collectNews()
            }
            MOVIE_DETAIL -> {
                binding.apply {
                    tvRelatedTitle.text = "감독의 다른 영화"
                }
                collectMovie()
            }
            MUSIC_DETAIL -> {
                binding.apply {
                    tvRelatedTitle.text = "가수의 다른 음악"
                }
                collectMusic()
            }
        }
    }

    private fun collectVideo() {
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

    }

    private fun collectBook() {
        //관련 도서
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.books.collectLatest {
                    if (it.isNotEmpty()) {
                        relatedAdapter.submitList(it)
                    }
                }
            }
        }

    }

    private fun collectMusic() {
        //가수의 다른 음악
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.music.collectLatest {
                    if (it.isNotEmpty()) {

                    }
                }
            }
        }
    }

    private fun collectNews() {
        //관련 뉴스
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.news.collectLatest {
                    if (it.isNotEmpty()) {
                        newsAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun collectGame() {
        //관련 게임
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.games.collectLatest {
                    if (it.isNotEmpty()) {

                    }
                }
            }
        }
    }

    private fun collectMovie() {
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