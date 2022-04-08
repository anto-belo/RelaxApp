package com.ab.relax.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ab.relax.view.home.HomeScreen
import com.ab.relax.view.listen.ListenScreen
import com.ab.relax.view.main.BottomBarScreen
import com.ab.relax.view.profile.ProfileScreen

@Composable
fun BottomNavGraph(bottomNavController: NavHostController, navController: NavHostController) {
    NavHost(navController = bottomNavController, startDestination = BottomBarScreen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Listen.route) {
            ListenScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}