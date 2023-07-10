package com.example.littlelemonfinal.model.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.data.MenuNetworkData
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val ktorApiClient: HttpClient) : HomeDataSource {
    override suspend fun fetchMenuItems(): List<MenuItemNetwork> {
        val response: MenuNetworkData =
            ktorApiClient.get("Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body()
        return response.menu ?: listOf();

//        return  ktorApiClient.getMenu()
    }

    override fun loadMenuFromDatabase(): LiveData<List<MenuItemNetwork>> {
        TODO("Not yet implemented")
    }
}