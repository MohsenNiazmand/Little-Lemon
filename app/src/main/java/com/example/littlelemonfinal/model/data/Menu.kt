package com.example.littlelemonfinal.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
@Serializable
data class MenuNetworkData(
    val menu: List<MenuItemNetwork>
)
@Serializable
@Entity
data class MenuItemNetwork (
    @PrimaryKey
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: String,
    val title: String
)