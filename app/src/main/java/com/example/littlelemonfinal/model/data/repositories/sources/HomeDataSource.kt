package com.example.littlelemonfinal.model.data.repositories.sources

import androidx.lifecycle.LiveData
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import dagger.Module
import dagger.hilt.InstallIn

interface HomeDataSource {

    suspend fun fetchMenuItems(): List<MenuItemNetwork>
    fun loadMenuFromDatabase() : LiveData<List<MenuItemNetwork>>

}