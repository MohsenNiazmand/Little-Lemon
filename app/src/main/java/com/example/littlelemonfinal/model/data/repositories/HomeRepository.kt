package com.example.littlelemonfinal.model.data.repositories

import android.view.Menu
import androidx.lifecycle.LiveData
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.data.MenuNetworkData
interface HomeRepository {
    suspend fun fetchMenuItems(): List<MenuItemNetwork>
    fun loadMenuFromDatabase() : LiveData<List<MenuItemNetwork>>
}