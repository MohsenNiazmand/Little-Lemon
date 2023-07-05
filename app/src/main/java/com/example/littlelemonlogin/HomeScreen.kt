package com.example.littlelemonlogin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController


@Composable
    fun  HomeScreen(navController: NavController){
        Column(
            Modifier
                .background(color = Color.Red)
                .fillMaxSize()
                .fillMaxWidth()) {
            Text("Home")
            Button(onClick = { navController.navigate(Profile.route)}) {
                Text(text = "Profile")
            }
        }
    }


//    @Composable
//    @Preview
//    fun  HomeScreenPreview(){
//        HomeScreen()
//    }


