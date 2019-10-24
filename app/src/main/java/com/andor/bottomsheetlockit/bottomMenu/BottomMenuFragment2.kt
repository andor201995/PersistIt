package com.andor.bottomsheetlockit.bottomMenu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.andor.bottomsheetlockit.R
import kotlinx.android.synthetic.main.fragment_bottom_menu2.*

/**
 * A simple [Fragment] subclass.
 */
class BottomMenuFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_menu2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        next_button_bottom_menu2.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_bottomMenuFragment2_to_primaryMenuFragment)
        }
    }
}
