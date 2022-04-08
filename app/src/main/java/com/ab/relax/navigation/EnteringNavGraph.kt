package com.ab.relax.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ab.relax.data.entity.Photo
import com.ab.relax.view.about.AboutScreen
import com.ab.relax.view.bmi.BmiScreen
import com.ab.relax.view.editprofile.EditScreen
import com.ab.relax.view.firstenter.FirstEnterScreen
import com.ab.relax.view.guide.GuideScreen
import com.ab.relax.view.login.LoginScreen
import com.ab.relax.view.main.MainScreen
import com.ab.relax.view.menu.MenuScreen
import com.ab.relax.view.photo.PhotoScreen
import com.ab.relax.view.registration.RegistrationScreen
import com.ab.relax.view.splash.SplashScreen

@Composable
fun EnteringNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(navController = navController)
        }
        composable(Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(Screen.Photo.route) {
            val photo = navController.previousBackStackEntry?.savedStateHandle?.get<Photo>("info")
            photo?.let { PhotoScreen(navController = navController, photo = it) }
        }
        composable(Screen.Menu.route) {
            MenuScreen(navController = navController)
        }
        composable(Screen.EditProfile.route) {
            EditScreen(navController = navController)
        }
        composable(Screen.CalculateBmi.route) {
            BmiScreen(navController = navController)
        }
        composable(Screen.AboutDeveloper.route) {
            AboutScreen(navController = navController)
        }
        composable(Screen.Guide.route) {
            GuideScreen(navController = navController)
        }
        composable(Screen.FirstEnter.route) {
            FirstEnterScreen(navController = navController)
        }
    }
}
