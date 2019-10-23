package com.andor.bottomsheetlockit.core

data class AppState(
    val focusType: TextFocusType = TextFocusType.None,
    val bottomMenuState: BottomMenuState = BottomMenuState.Invisible
)

sealed class BottomMenuState {
    object Invisible : BottomMenuState()
    sealed class Visible : BottomMenuState() {
        object BottomSheet1 : Visible()
        object BottomSheet2 : Visible()
        object BottomSheet3 : Visible()
    }
}

sealed class TextFocusType {
    object None : TextFocusType()
    object Focused : TextFocusType()
}

sealed class EventType {
    sealed class Text : EventType() {
        object Down : Text()
        object SelectionChanged : Text()
    }
}

