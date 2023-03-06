package yellowc.app.allrank.ui.music.korean

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentKoreanBinding
import yellowc.app.allrank.ui.base.BaseFragment

class KoreanFragment : BaseFragment<FragmentKoreanBinding>(R.layout.fragment_korean) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_korean, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}