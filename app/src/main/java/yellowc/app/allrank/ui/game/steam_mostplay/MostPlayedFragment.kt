package yellowc.app.allrank.ui.game.steam_mostplay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentMostPlayedBinding
import yellowc.app.allrank.ui.base.BaseFragment

class MostPlayedFragment : BaseFragment<FragmentMostPlayedBinding>(R.layout.fragment_most_played) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}