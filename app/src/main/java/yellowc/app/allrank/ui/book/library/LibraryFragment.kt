package yellowc.app.allrank.ui.book.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yellowc.app.allrank.R
import yellowc.app.allrank.databinding.FragmentLibraryBinding
import yellowc.app.allrank.ui.base.BaseFragment

class LibraryFragment : BaseFragment<FragmentLibraryBinding>(R.layout.fragment_library) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}