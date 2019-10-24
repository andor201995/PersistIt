package com.andor.bottomsheetlockit.bottomMenu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andor.bottomsheetlockit.R
import com.andor.bottomsheetlockit.core.MainViewModel

/**
 * A simple [Fragment] subclass.
 */
class BaseBottomSheetFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(MainViewModel::class.java)
//        handleAppStateChange(viewModel.getAppStateStream().value!!)
        viewModel.getAppStateStream().observe(this, Observer {
            //            handleAppStateChange(it)
        })
    }

//    private fun handleAppStateChange(appState: AppState) {
//        val navControllerBottomSheet =
//            NavHostFragment.findNavController(this)
//        when (appState.bottomMenuState) {
//            is BottomMenuState.Visible.BottomSheet1 -> {
//                navControllerBottomSheet.navigate(R.id.action_baseBottomSheetFragment_to_bottomMenuFragment1)
//            }
//            is BottomMenuState.Visible.BottomSheet2 -> {
//                navControllerBottomSheet.navigate(R.id.action_baseBottomSheetFragment_to_bottomMenuFragment2)
//            }
//            is BottomMenuState.Visible.BottomSheet3 -> {
//                navControllerBottomSheet.navigate(R.id.action_baseBottomSheetFragment_to_bottomMenuFragment3)
//            }
//        }
//    }
}
