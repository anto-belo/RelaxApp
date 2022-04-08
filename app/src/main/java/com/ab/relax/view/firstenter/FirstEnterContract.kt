package com.ab.relax.view.firstenter

import com.ab.relax.view.base.UiEffect
import com.ab.relax.view.base.UiEvent
import com.ab.relax.view.base.UiState

class FirstEnterContract {

    sealed class Event : UiEvent {
        data class OnFinishButtonClick(
            val phone: String,
            val weight: String,
            val pressure: String,
            val birthday: String
        ) : Event()
    }

    data class State(
        val firstEnterState: FirstEnterState
    ) : UiState

    sealed class FirstEnterState {
        object Idle : FirstEnterState()
        object Loading : FirstEnterState()
        object Success : FirstEnterState()
    }

    sealed class Effect : UiEffect
}