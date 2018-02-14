package com.calvo.carolina.repository

import com.calvo.carolina.repository.network.json.JsonEntitiesParser
import com.calvo.carolina.repository.network.json.model.ListShopsJsonEntity
import com.calvo.carolina.repository.network.json.model.ShopJsonModel
import com.calvo.carolina.repository.util.ReadJsonFile
import org.junit.Assert.*
import org.junit.Test

class JSONParsingTests
{
    // TODO("Hacer test de parseo de actividades")
    @Test
    @Throws(Exception::class)
    fun given_valid_JsonString_parses_correcly()
    {
        // Arrange
        val shopJson = ReadJsonFile().loadJSONFromAsset("shop.json")
        assertTrue(shopJson.isNotEmpty())

        val parser = JsonEntitiesParser()
        // Act
        val shop = parser.parse<ShopJsonModel>(shopJson)

        // Assert
        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)
        assertEquals("40.4180563", shop.gps_lat)
    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_JsonString_latitude_parses_correcly()
    {
        // Arrange
        val shopJson = ReadJsonFile().loadJSONFromAsset("shopWrongLatitude.json")
        assertTrue(shopJson.isNotEmpty())

        val parser = JsonEntitiesParser()
        // Act
        val shop = parser.parse<ShopJsonModel>(shopJson)

        // Assert
        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)
        assertEquals("40.4180563,275 ", shop.gps_lat)
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_JsonString_parses_correcly_allShops()
    {
        // Arrange
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shops.json")
        assertTrue(shopsJson.isNotEmpty())

        val parser = JsonEntitiesParser()
        // Act
        val shops = parser.parse<ListShopsJsonEntity>(shopsJson)

        // Assert
        assertNotNull(shops)
        assertTrue(shops.result.size > 0)
        val shop = shops.result.first()
        assertEquals("Cortefiel - Preciados", shop.name)
        assertEquals("40.4180563", shop.gps_lat)
    }
}