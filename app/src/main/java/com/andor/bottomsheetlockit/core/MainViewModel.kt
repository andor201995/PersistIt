package com.andor.bottomsheetlockit.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val appStateStream: MutableLiveData<AppState> = MutableLiveData(
        AppState()
    )

    fun getAppStateStream(): LiveData<AppState> {
        return appStateStream
    }

    fun onTextFocusChanged(textFocusType: TextFocusType) {
        if (textFocusType == TextFocusType.Focused && textFocusType != appStateStream.value!!.focusType
            && appStateStream.value!!.bottomMenuState is BottomMenuState.Visible
        ) {
            hideBottomSheet()
        }
        appStateStream.value = appStateStream.value?.copy(focusType = textFocusType)
    }

    fun hideBottomSheet() {
        appStateStream.value =
            appStateStream.value?.copy(bottomMenuState = BottomMenuState.Invisible)
    }

    fun showBottomSheet(bottomMenuState: BottomMenuState.Visible) {
        appStateStream.value = appStateStream.value?.copy(bottomMenuState = bottomMenuState)
    }

    fun handleEvent(event: EventType) {
        val appState = appStateStream.value!!
        when (event) {
            is EventType.Text.Down -> {
                if (appState.focusType == TextFocusType.Focused) {
                    hideBottomSheet()
                }
            }
            is EventType.Text.SelectionChanged -> {
                if (appState.focusType == TextFocusType.Focused) {
                    hideBottomSheet()
                }
            }
        }
    }

}