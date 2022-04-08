package com.ab.relax.di

import com.ab.relax.view.firstenter.FirstEnterViewModel
import com.ab.relax.view.home.HomeViewModel
import com.ab.relax.view.login.LoginViewModel
import com.ab.relax.view.menu.MenuViewModel
import com.ab.relax.view.photo.PhotoViewModel
import com.ab.relax.view.profile.ProfileViewModel
import com.ab.relax.view.registration.RegistrationViewModel
import org.koin.dsl.module

val appModule = module {
    single { RegistrationViewModel() }
    single { LoginViewModel() }
    single { ProfileViewModel() }
    single { PhotoViewModel(get()) }
    single { HomeViewModel() }
    single { MenuViewModel() }
    single { FirstEnterViewModel() }
}