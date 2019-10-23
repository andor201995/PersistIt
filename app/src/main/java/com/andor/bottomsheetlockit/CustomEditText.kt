package com.andor.bottomsheetlockit

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText

class CustomEditText : EditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(
        context: Context,
        attributes: AttributeSet,
        defStyleAttribute: Int
    ) : super(context, attributes, defStyleAttribute)

    private lateinit var textEventListener: TextEventListener

    fun setEventListener(textEventListener: TextEventListener) {
        this.textEventListener = textEventListener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (::textEventListener.isInitialized) {
                textEventListener.onDown()
            }
            performClick()
        }
        return super.onTouchEvent(event)
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        if (::textEventListener.isInitialized) {
            textEventListener.onSelectionChanged()
        }
    }
}