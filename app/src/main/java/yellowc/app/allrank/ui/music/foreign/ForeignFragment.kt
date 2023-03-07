package yellowc.app.allrank.ui.music.foreign

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentForeignBinding
import yellowc.app.allrank.ui.base.BaseFragment

class ForeignFragment : BaseFragment<FragmentForeignBinding>(R.layout.fragment_foreign) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_foreign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}