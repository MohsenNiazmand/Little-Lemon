package com.example.littlelemonfinal.model.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.services.MenuItemDao
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(private val menuItemDao: MenuItemDao) : HomeDataSource {
    override suspend fun fetchMenuItems(): List<MenuItemNetwork> {
        TODO("Not yet implemented")
    }

    override fun loadMenuFromDatabase(): LiveData<List<MenuItemNetwork>> {
       return  menuItemDao.getAll();
    }
}