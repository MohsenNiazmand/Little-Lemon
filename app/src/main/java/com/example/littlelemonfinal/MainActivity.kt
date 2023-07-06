package com.example.littlelemonfinal

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemonfinal.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
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

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "littleLemon.db").build()
    }


    private val menuItemsLiveData = MutableLiveData<List<MenuItemNetwork>>()

    private suspend fun getMenu(): List<MenuItemNetwork> {
        val response: MenuNetworkData =
            client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body()
        return response.menu ?: listOf();
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        token.value = sharedPreferences.getString("userName", "");

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val items=getMenu();
                saveMenuToDatabase(items)
            }
        }





        setContent {
            LittleLemonTheme {
                val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
                var menuItems= emptyList<MenuItemRoom>()

                menuItems=databaseMenuItems;

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


