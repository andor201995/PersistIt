package com.andor.bottomsheetlockit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_bottom_menu.*

/**
 * A simple [Fragment] subclass.
 */
class BottomMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        next_button.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_bottomMenuFragment_to_primaryMenuFragment)
        }
    }
}
