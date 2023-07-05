package com.example.littlelemonlogin

import kotlinx.serialization.Serializable
@Serializable
data class MenuNetworkData(
    val menu: List<MenuItemNetwork>
)
@Serializable
data class MenuItemNetwork (
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: String,
    val title: String
)