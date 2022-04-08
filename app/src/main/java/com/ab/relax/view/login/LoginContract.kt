package com.ab.relax.view.login

import com.ab.relax.view.base.UiEffect
import com.ab.relax.view.base.UiEvent
import com.ab.relax.view.base.UiState

class LoginContract {

    sealed class Event : UiEvent {
        data class OnAuthButtonClick(val email: String, val password: String) : Event()
        object OnRegisterButtonClick : Event()
    }

    data class State(val loginState: LoginState) : UiState

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        object Menu : LoginState()
        object FirstEnter : LoginState()
        object Register : LoginState()
    }

    sealed class Effect : UiEffect {
        object ShowIncorrectDataToast : Effect()
        object ShowWrongParamsToast : Effect()
    }
}