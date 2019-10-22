package com.andor.bottomsheetlockit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        bottomSheetBehavior =
            BottomSheetBehavior.from(findViewById<View>(R.id.bottomSheetNavHostFragment))
        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
            }
            override fun onStateChanged(p0: View, p1: Int) {
                when (bottomSheetBehavior.state) {
                    BottomSheetBehavior.STATE_DRAGGING -> bottomSheetBehavior.state =
                        BottomSheetBehavior.STATE_EXPANDED
                }
            }
        })

        button_open_bottom_sheet.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onBackPressed() {
        val navController =
            Navigation.findNavController(findViewById(R.id.bottomSheetNavHostFragment))
        if (navController.currentDestination!!.id == R.id.bottomMenuFragment && bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            return
        }
        super.onBackPressed()
    }
}
