package com.example.littlelemonfinal.model.data.repositories

import androidx.lifecycle.LiveData
import com.example.littlelemonfinal.di.HomeLocalQualifier
import com.example.littlelemonfinal.di.HomeRemoteQualifier
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.data.repositories.sources.HomeDataSource
import com.example.littlelemonfinal.model.data.repositories.sources.HomeLocalDataSource
import com.example.littlelemonfinal.model.data.repositories.sources.HomeRemoteDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    @HomeRemoteQualifier private val homeRemoteDataSource: HomeDataSource,
    @HomeLocalQualifier private  val homeLocalDataSource: HomeDataSource) : HomeRepository {
    override suspend fun fetchMenuItems(): List<MenuItemNetwork> {
      return  homeRemoteDataSource.fetchMenuItems();
    }

    override fun loadMenuFromDatabase(): LiveData<List<MenuItemNetwork>> {
        return  homeLocalDataSource.loadMenuFromDatabase()
    }
}