package com.calvo.carolina.repository.db.models


data class ShopDAOModel(
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