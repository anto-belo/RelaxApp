package com.ab.relax.view.profile

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.ab.relax.App
import com.ab.relax.data.StoreUserId
import com.ab.relax.data.entity.Photo
import com.ab.relax.navigation.Screen
import com.ab.relax.view.base.BaseAction
import com.ab.relax.view.profile.ProfileContract.Effect.UpdateList
import com.ab.relax.view.profile.ProfileContract.ProfileState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch


class ProfileAction(
    composableScope: CoroutineScope,
    val context: Context,
    val viewModel: ProfileViewModel,
    val isLoadingState: MutableState<Boolean>,
    val dataWithUpdater: Pair<MutableState<MutableList<Photo>>, MutableState<Boolean>>,
    val navController: NavHostController
) : BaseAction(composableScope) {

    override fun onState() {
        composableScope.launch {
            viewModel.uiState.collect { state ->
                composableScope.ensureActive()
                when (state.profileState) {
                    is ProfileState.Logout -> {
                        viewModel.clearState()
                        navController.popBackStack()
                        navController.navigate(Screen.Splash.route)
                        App.instance.user = null
                        StoreUserId(context).saveUserId(-1L)
                        cancelScope()
                    }
                    is ProfileState.Menu -> {
                        viewModel.clearState()
                        navController.navigate(Screen.Menu.route)
                        cancelScope()
                    }
                    is ProfileState.Image -> {
                        viewModel.clearState()
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "info", state.profileState.photo
                        )
                        navController.navigate(Screen.Photo.route)
                        cancelScope()
                    }
                    is ProfileState.Idle -> isLoadingState.value = false
                    is ProfileState.Loading -> isLoadingState.value = true
                }
            }
        }
    }

    override fun onEffect() {
        composableScope.launch {
            viewModel.effect.collect { effect ->
                composableScope.ensureActive()
                when (effect) {
                    is UpdateList -> {
                        val (photos, updater) = dataWithUpdater
                        updater.value = false
                        photos.value = effect.photos
                        updater.value = true
                    }
                }
            }
        }
    }


}