package com.example.littlelemonfinal.ui.navigation

interface Destinations {
    val route:String
}

object OnBoarding : Destinations {
    override val route= "OnBoarding"
}

object Home : Destinations {
    override val route= "Home"
}

object Profile : Destinations {
    override val route= "Profile"
}