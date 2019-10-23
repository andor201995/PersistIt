package com.andor.bottomsheetlockit.mainContent


import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.andor.bottomsheetlockit.MainActivity
import com.andor.bottomsheetlockit.R
import com.andor.bottomsheetlockit.core.EventType
import com.andor.bottomsheetlockit.core.MainViewModel
import com.andor.bottomsheetlockit.core.TextFocusType
import com.andor.bottomsheetlockit.core.custom.TextEventListener
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), TextEventListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(MainViewModel::class.java)

        button_open_bottom_sheet.setOnClickListener {
            viewModel.showBottomSheet()
        }

        button_open_pop_up.setOnClickListener {
            val actionModeCallback = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                object : ActionMode.Callback2() {
                    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                        mode!!.finish()
                        return true
                    }

                    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                        mode?.menuInflater?.inflate(R.menu.pop_up_menu, menu)
                        return true
                    }

                    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                        return true
                    }

                    override fun onDestroyActionMode(mode: ActionMode?) {
                    }
                }
            } else {
                object : ActionMode.Callback {
                    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                        mode!!.finish()
                        return true
                    }

                    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                        mode?.menuInflater?.inflate(R.menu.pop_up_menu, menu)
                        return true
                    }

                    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                        return true
                    }

                    override fun onDestroyActionMode(mode: ActionMode?) {
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                it.startActionMode(actionModeCallback, ActionMode.TYPE_FLOATING)
            }
        }

        editText.setEventListener(this)
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                viewModel.onTextFocusChanged(TextFocusType.Focused)
            } else {
                viewModel.onTextFocusChanged(TextFocusType.None)
            }
        }
        container.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                (activity as MainActivity).hideSoftKeyboard()
            }
        }
        button_next_fragment.setOnClickListener {
            val navController = NavHostFragment.findNavController(this)
            navController.navigate(R.id.action_mainFragment_to_primaryContentFragment)
        }
    }

    override fun onDown() {
        viewModel.handleEvent(EventType.Text.Down)
    }

    override fun onSelectionChanged() {
        viewModel.handleEvent(EventType.Text.SelectionChanged)
    }

}
