package com.calvo.carolina.business.model

import java.io.Serializable

data class Activity(override val id: Int,
                override val name: String,
                override val description: String,
                override val openingHours: String,
                override val address: String,
                override val latitude: Double,
                override val longitude: Double,
                override val image: String,
                override val logo: String): Serializable, Place
