package com.example.littlelemonfinal.model.data

import com.example.littlelemonfinal.model.services.MenuItemRoom
import kotlinx.serialization.Serializable
@Serializable
data class MenuNetworkData(
    val menu: List<MenuItemNetwork>
)
@Serializable
data class MenuItemNetwork (
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: String,
    val title: String
){
    fun toMenuItemRoom()= MenuItemRoom(id,category,description,image,price,title)
}