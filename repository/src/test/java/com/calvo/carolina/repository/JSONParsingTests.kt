package com.calvo.carolina.repository

import org.junit.Test

import com.calvo.carolina.repository.network.json.JsonEntitiesParser
import com.calvo.carolina.repository.network.json.model.ShopJsonModel
import com.calvo.carolina.repository.util.ReadJsonFile
import org.junit.Assert.*

class JSONParsingTests
{
    @Test
    @Throws(Exception::class)
    fun given_valid_JsonString_parses_correcly()
    {
        // Arrange
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shop.json")
        assertTrue(shopsJson.isNotEmpty())

        val parser = JsonEntitiesParser()
        // Act
        val shops = parser.parse<ShopJsonModel>(shopsJson)

        // Assert
        assertNotNull(shops)
        assertEquals("Cortefiel - Preciados", shops.name)
    }


}