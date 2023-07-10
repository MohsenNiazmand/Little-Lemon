package com.example.littlelemonfinal.model.services

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.littlelemonfinal.model.data.MenuItemNetwork

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItemNetwork")
    fun getAll(): LiveData<List<MenuItemNetwork>>

    @Insert
    fun insertAll(vararg menuItems: MenuItemNetwork)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemNetwork) == 0")
    fun isEmpty(): Boolean
}