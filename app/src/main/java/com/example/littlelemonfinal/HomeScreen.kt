package com.example.littlelemonfinal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemonfinal.ui.theme.GreenGrayDark
import com.example.littlelemonfinal.ui.theme.White
import com.example.littlelemonfinal.ui.theme.Yellow


@Composable
    fun  HomeScreen(navController: NavController){
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Surface(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Image",
                contentScale = ContentScale.Fit
            )
        }

        Surface(Modifier.height(300.dp), color = GreenGrayDark) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(12.dp)) {
                Text(
                    text = "Little Lemon",
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 30.sp,
                    color = Yellow

                )

                Row() {
                    Column() {
                        Text(text = "Chicago", fontSize = 24.sp, color = White)
                        Box(Modifier.padding(top = 12.dp)) {
                            Text(
                                text = "We are a family owned \nMediterranean restaurant.\nfocused on traditional\nrecipes served with a\nmodern twist.",
                                color = White
                            )
                        }
                    }
                    Box(
                        Modifier
                            .width(260.dp)
                            .height(160.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.hero_image),
                            contentScale = ContentScale.Fit,
                            contentDescription = "Hero Image",
                            modifier = Modifier.clip(
                                RoundedCornerShape(12.dp)
                            )
                        )
                    }
                }

                TextField(value = "Enter Search phrase", onValueChange = {}, modifier = Modifier.fillMaxWidth().padding(top = 12.dp, start = 12.dp, end = 12.dp).background(color = White))
            }



        }


    }


    }


//    @Composable
//    @Preview
//    fun  HomeScreenPreview(){
//        HomeScreen()
//    }


