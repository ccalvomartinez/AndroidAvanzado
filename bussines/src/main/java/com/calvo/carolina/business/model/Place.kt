package com.calvo.carolina.business.model

import java.io.Serializable

interface Place: Serializable
{
    val id: Int
    val name: String
    val description: String
    val openingHours: String
    val address: String
    val latitude: Double
    val longitude: Double
    val image: String
    val logo: String
}