package com.vlad.kozyr.catfacts.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer<S : State, A : Action>(initialState: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S>
        get() = _state

    fun sendAction(action: A) {
        reduce(_state.value, action)
    }

    fun setState(newState: S) {
        _state.tryEmit(newState)
    }

    abstract fun reduce(oldState: S, action: A)
}

abstract class BaseViewModel<S : State> : ViewModel() {

    abstract val state: Flow<S>
}

interface State

interface Action
