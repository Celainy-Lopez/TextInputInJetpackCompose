package edu.ucne.textinputinjetpackcompose.presentation

sealed class UiEvent {
    object NavigateUp : UiEvent()
    data class ShowSnackbar(val message: String) : UiEvent()
}