package com.ab.relax.view.profile

import android.graphics.Bitmap
import com.ab.relax.data.entity.Photo
import com.ab.relax.view.base.UiEffect
import com.ab.relax.view.base.UiEvent
import com.ab.relax.view.base.UiState

class ProfileContract {

    sealed class Event : UiEvent {
        data class OnLoadImageButtonClick(val bitmap: Bitmap?) : Event()
        data class OnOpenImageButtonClick(val photo: Photo) : Event()
        object OnLogoutButtonClick : Event()
        object OnMenuButtonClick : Event()
    }

    data class State(val profileState: ProfileState) : UiState

    sealed class ProfileState {
        object Idle : ProfileState()
        object Loading : ProfileState()
        object Logout : ProfileState()
        object Menu : ProfileState()
        data class Image(val photo: Photo) : ProfileState()
    }

    sealed class Effect : UiEffect {
        data class UpdateList(val photos: MutableList<Photo>) : Effect()
    }
}