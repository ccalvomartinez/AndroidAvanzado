package com.calvo.carolina.repository

import com.calvo.carolina.repository.network.json.JsonEntitiesParser
import com.calvo.carolina.repository.network.json.model.ActivityJsonModel
import com.calvo.carolina.repository.network.json.model.ListActivitiesJsonEntity
import com.calvo.carolina.repository.network.json.model.ListShopsJsonEntity
import com.calvo.carolina.repository.network.json.model.ShopJsonModel
import com.calvo.carolina.repository.util.ReadJsonFile
import org.junit.Assert.*
import org.junit.Test

class JSONParsingTests
{
     @Test
    @Throws(Exception::class)
    fun given_valid_JsonStringShop_parses_correcly()
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
        assertEquals("40.4180563", shop.gps_lat.trim())
    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_JsonStringShop_latitude_parses_correcly()
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
    fun given_valid_JsonStringShop_parses_correcly_allShops()
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
        assertEquals("40.4180563", shop.gps_lat.trim())
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_JsonStringActivity_parses_correcly()
    {
        // Arrange
        val activityJson = ReadJsonFile().loadJSONFromAsset("activity.json")
        assertTrue(activityJson.isNotEmpty())

        val parser = JsonEntitiesParser()
        // Act
        val activity = parser.parse<ActivityJsonModel>(activityJson)

        // Assert
        assertNotNull(activity)
        assertEquals("Tour del Bernabéu", activity.name)
        assertEquals("40.4520478", activity.gps_lat.trim())
    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_JsonStringActivity_latitude_parses_correcly()
    {
        // Arrange
        val activityJson = ReadJsonFile().loadJSONFromAsset("activityWrongLatitude.json")
        assertTrue(activityJson.isNotEmpty())

        val parser = JsonEntitiesParser()
        // Act
        val activity = parser.parse<ActivityJsonModel>(activityJson)

        // Assert
        assertNotNull(activity)
        assertEquals("Tour del Bernabéu", activity.name)
        assertEquals("40.4180563,275", activity.gps_lat.trim())
    }

    @Test
    @Throws(Exception::class)
    fun given_valid_JsonStringActivity_parses_correcly_allShops()
    {
        // Arrange
        val activitiesJson = ReadJsonFile().loadJSONFromAsset("activities.json")
        assertTrue(activitiesJson.isNotEmpty())

        val parser = JsonEntitiesParser()
        // Act
        val activities = parser.parse<ListActivitiesJsonEntity>(activitiesJson)

        // Assert
        assertNotNull(activities)
        assertTrue(activities.result.size > 0)
        val shop = activities.result.first()
        assertEquals("Tour del Bernabéu", shop.name)
        assertEquals("40.4520478", shop.gps_lat.trim())
    }
}