package com.example.littlelemonlogin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

class NavigationComposable {


    @Composable
    fun Navigation(navController: NavHostController){
        val selectedIndex = rememberSaveable {
            mutableStateOf(0)
        }
        val destinations = listOf<Destinations>(
            OnBoarding,
            Home,
            Profile
        )
        NavHost(navController =navController , graph = navController.graph )


    }


}