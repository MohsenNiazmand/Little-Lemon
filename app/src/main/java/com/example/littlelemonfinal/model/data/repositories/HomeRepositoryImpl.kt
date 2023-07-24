package com.example.littlelemonfinal.model.data.repositories

import androidx.lifecycle.LiveData
import com.example.littlelemonfinal.di.HomeLocalQualifier
import com.example.littlelemonfinal.di.HomeRemoteQualifier
import com.example.littlelemonfinal.model.common.BaseResult
import com.example.littlelemonfinal.model.data.ErrorResponse
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.model.data.repositories.sources.HomeDataSource
import com.example.littlelemonfinal.model.data.repositories.sources.HomeLocalDataSource
import com.example.littlelemonfinal.model.data.repositories.sources.HomeRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    @HomeRemoteQualifier private val homeRemoteDataSource: HomeDataSource,
    @HomeLocalQualifier private val homeLocalDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun fetchMenuItems(): Flow<BaseResult<List<MenuItemNetwork>, ErrorResponse>> {
        return homeRemoteDataSource.fetchMenuItems();
    }
    override fun loadMenuFromDatabase(): Flow<List<MenuItemNetwork>> {
        return homeLocalDataSource.loadMenuFromDatabase()
    }

    override fun saveMenuToDatabase(menuItems:List<MenuItemNetwork>) {
        return homeLocalDataSource.saveMenuToDatabase(menuItems)
    }
}