package com.example.littlelemonfinal.model.data.repositories

import android.view.Menu
import androidx.lifecycle.LiveData
import com.example.littlelemonfinal.model.common.BaseResult
import com.example.littlelemonfinal.model.data.ErrorResponse
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.data.MenuNetworkData
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun fetchMenuItems(): Flow<BaseResult<List<MenuItemNetwork>, ErrorResponse>>
    fun loadMenuFromDatabase() : Flow<List<MenuItemNetwork>>
    fun saveMenuToDatabase( menuItems: List<MenuItemNetwork>) : Unit
}