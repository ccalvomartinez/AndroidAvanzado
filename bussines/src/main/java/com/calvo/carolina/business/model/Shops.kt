package com.calvo.carolina.business.model

import java.io.Serializable
import java.util.*

data class Shop(val id: Int,
                val name: String,
                val description: String,
                val openingHours: String,
                val address: String,
                val latitude: Double,
                val longitude: Double,
                val image: String,
                val logo: String): Serializable {

    init
    {
        Shops(ArrayList())
    }
}
class Shops (private val shops: MutableList<Shop>): Aggregate<Shop>
{
    // Otra manera de poner la función
    override fun all() = shops

    override fun count() = shops.size

    override fun get(position: Int): Shop
    {
        return shops[position]
    }

    override fun add(element: Shop)
    {
        shops.add(element)
    }

    override fun delete(position: Int)
    {
        if (position < shops.size)
        {
            shops.removeAt(position)
        }
    }

    override fun delete(element: Shop)
    {
        shops.remove(element)
    }



}