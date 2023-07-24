package com.example.littlelemonfinal.model.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.littlelemonfinal.model.common.BaseResult
import com.example.littlelemonfinal.model.data.ErrorResponse
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.services.MenuItemDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(private val menuItemDao: MenuItemDao) : HomeDataSource {
    override suspend fun fetchMenuItems(): Flow<BaseResult<List<MenuItemNetwork>, ErrorResponse>> {
        TODO("Not yet implemented")
    }

    override fun loadMenuFromDatabase():  Flow<List<MenuItemNetwork>> {
       return  menuItemDao.getAll();
    }

    override fun saveMenuToDatabase(menuItems: List<MenuItemNetwork>) {
        menuItemDao.insertAll(menuItems)
    }
}