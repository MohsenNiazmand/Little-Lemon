package com.example.littlelemonfinal.model.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.littlelemonfinal.model.common.BaseResult
import com.example.littlelemonfinal.model.data.ErrorResponse
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.data.MenuNetworkData
import com.example.littlelemonfinal.model.services.MenuItemDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.request
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.Reader
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val ktorApiClient: HttpClient,
    private val menuItemDao: MenuItemDao
) : HomeDataSource {
    override suspend fun fetchMenuItems(): Flow<BaseResult<List<MenuItemNetwork>, ErrorResponse>> {
        return flow {
            val response =
                ktorApiClient.get("Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")

            if (response.body<MenuNetworkData>().menu.isNotEmpty()) {
                val body = response.body<MenuNetworkData>()


                emit(BaseResult.Success(body.menu))

            } else {
                val error: Reader = response.body()
                val type = object : TypeToken<ErrorResponse>() {}.type
                val err =
                    Gson().fromJson<ErrorResponse>(error, type)!!
                emit(BaseResult.Error(err))
            }
        }

    }

    override fun loadMenuFromDatabase(): Flow<List<MenuItemNetwork>> {
        TODO("Not yet implemented")
    }

    override fun saveMenuToDatabase(menuItems: List<MenuItemNetwork>) {
        TODO("Not yet implemented")
    }
}