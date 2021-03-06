package com.ab.relax.view.photo

import com.ab.relax.view.base.BaseViewModel
import com.ab.relax.view.photo.PhotoContract.Event
import com.ab.relax.view.photo.PhotoContract.PhotoState
import com.ab.relax.view.profile.ProfileViewModel

class PhotoViewModel(private val profileViewModel: ProfileViewModel) :
    BaseViewModel<Event, PhotoContract.State, PhotoContract.Effect>() {

    override fun createInitialState(): PhotoContract.State {
        return PhotoContract.State(PhotoState.Idle)
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnCloseImageButtonClick -> {
                setState { copy(photoState = PhotoState.Close) }
            }
            is Event.OnDeleteImageButtonClick -> {
                profileViewModel.deleteImage(event.photo)
                setState { copy(photoState = PhotoState.Delete) }
            }
        }
    }

    override fun clearState() {
        setState { copy(photoState = PhotoState.Idle) }
    }
}
