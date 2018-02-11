package com.calvo.carolina.repository.models

 // TODO("Añadir todos los campos de los idiomas")
data class ShopEntity(
        val id: Long,
        val databaseID: Long,
        val name: String,
        val description: String,
        val latitude: Float,
        val longitude: Float,
        val image: String,
        val logo: String,
        val openingHours: String,
        val address: String
)