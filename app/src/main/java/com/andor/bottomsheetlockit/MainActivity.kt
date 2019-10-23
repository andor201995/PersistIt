package com.andor.bottomsheetlockit

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), TextEventListener {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomSheetBehavior =
            BottomSheetBehavior.from(findViewById<View>(R.id.bottomSheetNavHostFragment))
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.hideBottomSheet()
        bindAppStateStream()
    }

    private fun bindAppStateStream() {
        viewModel.getAppStateStream().observe(this, Observer {
            handleAppStateChange(it)
        })
    }

    private fun handleAppStateChange(appState: AppState) {
        if (appState.bottomMenuState is BottomMenuState.Visible) {
            hideSoftKeyboard()
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

    }

    override fun onStart() {
        super.onStart()
        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
            }

            override fun onStateChanged(p0: View, p1: Int) {
                when (bottomSheetBehavior.state) {
                    BottomSheetBehavior.STATE_DRAGGING -> viewModel.showBottomSheet()

                }
            }
        })

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
        container.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                hideSoftKeyboard()
            }
        }
    }

    private fun hideSoftKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDown() {
        viewModel.handleEvent(EventType.Text.Down)
    }

    override fun onSelectionChanged() {
        viewModel.handleEvent(EventType.Text.SelectionChanged)
    }

    override fun onBackPressed() {
        val navController =
            Navigation.findNavController(findViewById(R.id.bottomSheetNavHostFragment))
        if (navController.currentDestination!!.id == R.id.bottomMenuFragment && bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            viewModel.hideBottomSheet()
            return
        }
        super.onBackPressed()
    }
}
