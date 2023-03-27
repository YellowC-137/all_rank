package yellowc.app.allrank.ui.movie.reservation

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
import yellowc.app.allrank.databinding.FragmentReservationBinding
import yellowc.app.allrank.ui.base.BaseAdapter
import yellowc.app.allrank.ui.base.BaseFragment
import yellowc.app.allrank.ui.movie.MovieFragmentDirections
import yellowc.app.allrank.util.JSOUP_RESERVATION
import yellowc.app.allrank.util.MOVIE_DETAIL
import yellowc.app.allrank.util.RESERVATION_URL

@AndroidEntryPoint
class ReservationFragment :
    BaseFragment<FragmentReservationBinding>(R.layout.fragment_reservation) {
    private val viewModel: ReservationViewModel by viewModels()
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
        viewModel.getReservation(RESERVATION_URL, JSOUP_RESERVATION)
        binding.reservationRcv.adapter = adapter

        binding.refreshLayout.setColorSchemeColors( resources.getColor(R.color.main) )
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getReservation(RESERVATION_URL, JSOUP_RESERVATION)
            binding.refreshLayout.isRefreshing = false
        }
        collectFlow()
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.reservation.collectLatest {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }

}