package com.andor.bottomsheetlockit.mainContent


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.andor.bottomsheetlockit.R
import com.andor.bottomsheetlockit.core.MainViewModel
import kotlinx.android.synthetic.main.fragment_primary_content.*

/**
 * A simple [Fragment] subclass.
 */
class PrimaryContentFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_primary_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(MainViewModel::class.java)
        button_bottom_sheet_1.setOnClickListener {
            viewModel.showBottomSheet()
        }
    }
}
