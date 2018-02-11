package com.calvo.carolina.repository.models

data class ShopEntity(
         val id: Long,
         val databaseID: Long,
         val name: String,
         val description_es: String,
         val description_en: String,
         val latitude: Float,
         val longitude: Float,
         val image: String,
         val logo: String,
         val openingHours_es: String,
         val openingHours_en: String,
         val address: String
)