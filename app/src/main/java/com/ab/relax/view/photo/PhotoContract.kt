package com.ab.relax.view.photo

import com.ab.relax.data.entity.Photo
import com.ab.relax.view.base.UiEffect
import com.ab.relax.view.base.UiEvent
import com.ab.relax.view.base.UiState

class PhotoContract {

    sealed class Event : UiEvent {
        data class OnDeleteImageButtonClick(val photo: Photo) : Event()
        object OnCloseImageButtonClick : Event()
    }

    data class State(val photoState: PhotoState) : UiState

    sealed class PhotoState {
        object Idle : PhotoState()
        object Delete : PhotoState()
        object Close : PhotoState()
    }

    sealed class Effect : UiEffect {}
}