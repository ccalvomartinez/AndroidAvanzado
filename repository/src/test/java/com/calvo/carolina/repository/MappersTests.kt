package com.calvo.carolina.repository

import com.calvo.carolina.repository.mappers.map
import org.junit.Test

import com.calvo.carolina.repository.network.json.JsonEntitiesParser
import com.calvo.carolina.repository.network.json.model.ShopJsonModel
import com.calvo.carolina.repository.util.ReadJsonFile
import org.junit.Assert.*

class MappersTests
{

    @Test
    @Throws(Exception::class)
    fun given_ShopJsonModel_Maps_To_ShopDAOModel()
    {
        // Arrange
        val shopJsonModel = ShopJsonModel(
                1,
                "MiTienda",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                gps_lat = "1.0",
                gps_lon = "2.0")

        // Act

        val shopDAOModel = shopJsonModel.map()

        //Assert
        assertNotNull(shopDAOModel)
        assertEquals(shopJsonModel.id, shopDAOModel.id)
        assertEquals(shopJsonModel.name,shopDAOModel.name)
        assertEquals(1.0f, shopDAOModel.latitude, 0.1f)
        assertEquals(2.0f, shopDAOModel.longitude, 0.1f)

    }

    @Test
    @Throws(Exception::class)
    fun given_ShopJsonModel_WithWrongLatitude_Maps_To_ShopDAOModel()
    {
        // Arrange
        val shopJsonModel = ShopJsonModel(
                1,
                "MiTienda",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                gps_lat = "3499mmm",
                gps_lon = "2.0")

        // Act

        val shopDAOModel = shopJsonModel.map()

        //Assert
        assertNotNull(shopDAOModel)
        assertEquals(shopJsonModel.id, shopDAOModel.id)
        assertEquals(shopJsonModel.name,shopDAOModel.name)
        assertEquals(Float.NaN, shopDAOModel.latitude)
        assertEquals(2.0f, shopDAOModel.longitude, 0.1f)

    }
}