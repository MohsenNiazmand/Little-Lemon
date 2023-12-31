package com.example.littlelemonfinal.ui.views

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemonfinal.R
import com.example.littlelemonfinal.ui.navigation.Home
import com.example.littlelemonfinal.ui.theme.Black
import com.example.littlelemonfinal.ui.theme.GreenGrayDark
import com.example.littlelemonfinal.ui.theme.White
import com.example.littlelemonfinal.ui.theme.Yellow

@Composable
fun Onboarding(sharedPreferences: SharedPreferences, navController: NavController) {


    val mContext = LocalContext.current;
    var usernameText by remember { mutableStateOf(TextFieldValue("")) }
    var passwordText by remember { mutableStateOf(TextFieldValue("")) }
    var emailText by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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

        Surface(Modifier.height(140.dp), color = GreenGrayDark) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "Let's get to know you",
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = White,
                    fontSize = 30.sp
                )
            }
        }

        Surface(modifier = Modifier.padding(30.dp)) {
            Box(contentAlignment = Alignment.CenterStart) {
                Text(text = "Personal information", Modifier.fillMaxWidth())
            }
        }

        OutlinedTextField(value = usernameText,
            onValueChange = { it -> usernameText = it },
            label = ({ Text(text = "Username") }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
        OutlinedTextField(value = passwordText,
            onValueChange = { it -> passwordText = it },
            label = ({ Text(text = "Password") }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
        OutlinedTextField(value = emailText,
            onValueChange = { it -> emailText = it },
            label = ({ Text(text = "Email") }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {

                    if (usernameText.text.isNotBlank() && passwordText.text.isNotBlank() && emailText.text.isNotBlank()) {
                        sharedPreferences.edit().putString("userName", usernameText.text).apply()
                        sharedPreferences.edit().putString("password", passwordText.text).apply()
                        sharedPreferences.edit().putString("email", emailText.text).apply()
                        Toast.makeText(mContext, "Registration successful", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate(Home.route) {
                            popUpTo(0)
                        }


                    } else {
                        Toast.makeText(
                            mContext,
                            "Registration unsuccessful. Please enter all data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                },
                colors = ButtonDefaults.buttonColors(Yellow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register", color = Black)
            }

        }
    }


}

