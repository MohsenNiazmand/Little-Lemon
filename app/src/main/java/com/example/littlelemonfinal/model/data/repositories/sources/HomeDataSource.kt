package com.example.littlelemonfinal.model.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.littlelemonfinal.model.common.BaseResult
import com.example.littlelemonfinal.model.data.ErrorResponse
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import dagger.Module
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {

    suspend fun fetchMenuItems(): Flow<BaseResult<List<MenuItemNetwork>, ErrorResponse>>
    fun loadMenuFromDatabase() : Flow<List<MenuItemNetwork>>
    fun saveMenuToDatabase(menuItems: List<MenuItemNetwork>) : Unit
}