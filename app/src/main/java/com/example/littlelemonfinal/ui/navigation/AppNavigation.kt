package com.example.littlelemonfinal.ui.navigation

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonfinal.ui.views.HomeScreen
import com.example.littlelemonfinal.ui.views.Onboarding
import com.example.littlelemonfinal.ui.views.ProfileScreen


@Composable
fun AppNavigation(sharedPreferences: SharedPreferences, isLogged: Boolean) {
    val navController = rememberNavController()
    if (!isLogged) {
        NavHost(navController = navController, startDestination = OnBoarding.route) {

            composable(OnBoarding.route) {
                Onboarding(sharedPreferences = sharedPreferences, navController = navController)
            }
            composable(Home.route) {
                HomeScreen(navController = navController)
            }

            composable(Profile.route) {
                ProfileScreen(navController = navController, sharedPreferences = sharedPreferences)
            }


        }
    } else {
        NavHost(navController = navController, startDestination = Home.route) {

            composable(Home.route) {
                HomeScreen(navController = navController)
            }

            composable(Profile.route) {
                ProfileScreen(navController = navController, sharedPreferences = sharedPreferences)
            }
            composable(OnBoarding.route) {
                Onboarding(sharedPreferences = sharedPreferences, navController = navController)
            }

        }

    }


}


