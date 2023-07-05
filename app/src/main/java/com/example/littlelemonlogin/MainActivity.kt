package com.example.littlelemonlogin

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonlogin.ui.theme.LittleLemonLoginTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var token = MutableLiveData<String>()
    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val menuItemsLiveData = MutableLiveData<List<MenuItemNetwork>>()

    private suspend fun getMenu(): List<MenuItemNetwork> {
        val response: MenuNetworkData =
            client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body()
        return response.menu ?: listOf();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        token.value = sharedPreferences.getString("userName", "");
        lifecycleScope.launch {
            val menuItems = getMenu();
            runOnUiThread {
                menuItemsLiveData.value = menuItems;
                Toast.makeText(
                    applicationContext,
                    menuItemsLiveData.value!!.size.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }



        setContent {
            LittleLemonLoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                }
                if (token.value?.isNotEmpty()!!) {
                    MyNavigation(sharedPreferences = sharedPreferences, isLogged = true)
                } else
                    MyNavigation(sharedPreferences = sharedPreferences, isLogged = false)
            }
        }
    }


}


@Composable
private fun MyNavigation(sharedPreferences: SharedPreferences, isLogged: Boolean) {
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


