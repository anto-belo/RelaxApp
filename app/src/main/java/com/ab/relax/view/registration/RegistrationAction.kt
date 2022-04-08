package com.ab.relax.view.registration

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.ab.relax.App
import com.ab.relax.R
import com.ab.relax.navigation.Screen
import com.ab.relax.view.base.BaseAction
import com.ab.relax.view.registration.RegistrationContract.Effect
import com.ab.relax.view.registration.RegistrationContract.RegistrationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

class RegistrationAction(
    composableScope: CoroutineScope,
    val context: Context,
    val viewModel: RegistrationViewModel,
    val isLoadingState: MutableState<Boolean>,
    val navController: NavHostController
) : BaseAction(composableScope) {

    override fun onState() {
        composableScope.launch {
            viewModel.uiState.collect { state ->
                composableScope.ensureActive()
                when (state.registrationState) {
                    is RegistrationState.Success -> {
                        viewModel.clearState()
                        App.saveUserId(context)
                        navController.popBackStack()
                        navController.navigate(Screen.FirstEnter.route)
                        cancelScope()
                    }
                    is RegistrationState.Login -> {
                        viewModel.clearState()
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
                        cancelScope()
                    }
                    is RegistrationState.Idle -> {
                        isLoadingState.value = false
                    }
                    is RegistrationState.Loading -> {
                        isLoadingState.value = true
                    }
                }
            }
        }
    }

    override fun onEffect() {
        composableScope.launch {
            viewModel.effect.collect { effect ->
                composableScope.ensureActive()
                when (effect) {
                    is Effect.ShowIncorrectDataToast -> {
                        Toast.makeText(
                            context, context.getString(R.string.error_register), Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Effect.ShowWrongParamsToast -> {
                        Toast.makeText(
                            context, context.getString(R.string.error_fields_data), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}