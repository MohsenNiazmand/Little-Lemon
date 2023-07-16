package com.example.littlelemonfinal.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.data.MenuNetworkData
import com.example.littlelemonfinal.model.services.AppDatabase
import com.example.littlelemonfinal.ui.navigation.AppNavigation
import com.example.littlelemonfinal.ui.theme.LittleLemonTheme
import com.example.littlelemonfinal.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var token = MutableLiveData<String>()
    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

//    private val homeViewModel:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        token.value = sharedPreferences.getString("userName", "");



        setContent {
            LittleLemonTheme(darkTheme = false) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                }
                if (token.value?.isNotEmpty()!!) {
                    AppNavigation(sharedPreferences = sharedPreferences, isLogged = true)
                } else
                    AppNavigation(sharedPreferences = sharedPreferences, isLogged = false)
            }
        }
    }


}