package com.example.littlelemonfinal.model.services

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItemNetwork")
    fun getAll(): Flow<List<MenuItemNetwork>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(menuItems: List<MenuItemNetwork>)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemNetwork) == 0")
    fun isEmpty(): Boolean
}