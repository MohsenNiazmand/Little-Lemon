package com.example.littlelemonlogin
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


@Composable
    fun  ProfileScreen(sharedPreferences: SharedPreferences, navController: NavController){
        val  mContext= LocalContext.current;
        var firstNameText by remember { mutableStateOf(TextFieldValue("Mohsen")) }
        var lastnameText by remember { mutableStateOf(TextFieldValue("Niazmand")) }
        var emailText by remember { mutableStateOf(TextFieldValue("asdas@gmail.com")) }
        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface (modifier = Modifier
                .height(75.dp)
                .fillMaxWidth()
                .padding(20.dp)){

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Image",
                    contentScale = ContentScale.Fit
                )
            }

            Surface(Modifier.height(120.dp)) {
                Box() {

                }
            }

            Surface(modifier = Modifier.padding(30.dp)){
                Box(contentAlignment = Alignment.CenterStart) {
                    Text(text = "Personal information", Modifier.fillMaxWidth())
                }
            }

            OutlinedTextField(value = firstNameText, onValueChange = { it-> firstNameText=it }, label = ({ Text(text = "Username") }), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp))
            OutlinedTextField(value = lastnameText, onValueChange = { it-> lastnameText=it }, label = ({ Text(text = "Password") }), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp))
            OutlinedTextField(value = emailText, onValueChange = {it-> emailText=it }, label = ({ Text(text = "Email") }), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp))
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 40.dp, start = 20.dp, end = 20.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally,) {
                Button(onClick = {


                        sharedPreferences.edit().clear().apply()
                        Toast.makeText(mContext, "logout successful", Toast.LENGTH_SHORT).show()
                        navController.navigate(OnBoarding.route)



                }, colors = ButtonDefaults.buttonColors(Color(0xFF495E57)), modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Logout", color = Color(0xFFEDEFEE))
                }

            }
        }


    }





