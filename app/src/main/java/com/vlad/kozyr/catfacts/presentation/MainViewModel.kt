package com.vlad.kozyr.catfacts.presentation

import androidx.lifecycle.viewModelScope
import com.vlad.kozyr.catfacts.core.Action
import com.vlad.kozyr.catfacts.core.BaseViewModel
import com.vlad.kozyr.catfacts.core.Reducer
import com.vlad.kozyr.catfacts.core.State
import com.vlad.kozyr.catfacts.domain.FetchRandomCatImagesWithFactsUseCase
import com.vlad.kozyr.catfacts.domain.model.CatImageWithFact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val fetchRandomCatImagesWithFactsUseCase: FetchRandomCatImagesWithFactsUseCase
) : BaseViewModel<MainState>() {

    private val reducer = MainReducer(MainState.initial())

    init {
        fetchRandomCatImagesWithFacts()
    }

    fun fetchRandomCatImagesWithFacts() = viewModelScope.launch {
        try {
            reducer.sendAction(MainAction.Fetch)
            val catFacts = fetchRandomCatImagesWithFactsUseCase()
            reducer.sendAction(MainAction.ShowData(catFacts))
        } catch (ex: Exception) {
            reducer.sendAction(MainAction.ShowError(ex.localizedMessage.orEmpty()))
        }
    }

    private class MainReducer(initialState: MainState) :
        Reducer<MainState, MainAction>(initialState) {
        override fun reduce(oldState: MainState, action: MainAction) {
            when (action) {
                MainAction.Fetch -> setState(MainState.Loading)
                is MainAction.ShowData -> setState(MainState.Loaded(action.catFacts))
                is MainAction.ShowError -> setState(MainState.Error(error = action.message))
            }
        }
    }

    override val state: Flow<MainState> = reducer.state
}

sealed interface MainState : State {

    data object Loading : MainState
    data class Loaded(val catFacts: List<CatImageWithFact>) : MainState
    data class Error(val error: String) : MainState

    companion object {
        fun initial() = Loading
    }
}

sealed interface MainAction : Action {
    data object Fetch : MainAction
    data class ShowError(val message: String) : MainAction
    data class ShowData(val catFacts: List<CatImageWithFact>) : MainAction
}