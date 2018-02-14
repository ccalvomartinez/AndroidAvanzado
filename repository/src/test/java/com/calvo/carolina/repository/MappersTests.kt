package com.calvo.carolina.repository

import com.calvo.carolina.repository.mappers.map
import com.calvo.carolina.repository.network.json.model.ActivityJsonModel
import com.calvo.carolina.repository.network.json.model.ShopJsonModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class MappersTests
{

    @Test
    @Throws(Exception::class)
    fun given_ShopJsonModel_Maps_To_ShopEntityModel()
    {
        // Arrange
        val shopJsonModel = ShopJsonModel(
                1,
                "MiTienda",
                "http://imagen.com/imagen.jpg",
                "http://imagen.com/logo.jpg",
                "De 10 a 2 en",
                "De 10 a 2 es",
                description_en = "Descripcion en",
                description_es = "Descripcion es",
                address = "Direccion",
                gps_lat = "1.0",
                gps_lon = "2.0")

        // Act

        val shopEntity = shopJsonModel.map()

        //Assert
        assertNotNull(shopEntity)
        assertEquals(shopJsonModel.id, shopEntity.id)
        assertEquals(shopJsonModel.name,shopEntity.name)
        assertEquals(shopJsonModel.description_en, shopEntity.description_en)
        assertEquals(shopJsonModel.description_es, shopEntity.description_es)
        assertEquals(shopJsonModel.opening_hours_en, shopEntity.openingHours_en)
        assertEquals(shopJsonModel.opening_hours_es, shopEntity.openingHours_es)
        assertEquals(shopJsonModel.address, shopEntity.address)
        assertEquals(1.0f, shopEntity.latitude, 0.1f)
        assertEquals(2.0f, shopEntity.longitude, 0.1f)

    }

    @Test
    @Throws(Exception::class)
    fun given_ShopJsonModel_WithWrongLatitude_Maps_To_ShopEntityModel()
    {
        // Arrange
        val shopJsonModel = ShopJsonModel(
                1,
                "MiTienda",
                "http://imagen.com/imagen.jpg",
                "http://imagen.com/logo.jpg",
                "De 10 a 2 en",
                "De 10 a 2 es",
                description_en = "Descripcion en",
                description_es = "Descripcion es",
                address = "Direccion",
                gps_lat = "3499mmm",
                gps_lon = "2.0")

        // Act

        val shopEntity = shopJsonModel.map()

        //Assert
        assertNotNull(shopEntity)
        assertEquals(shopJsonModel.id, shopEntity.id)
        assertEquals(shopJsonModel.name,shopEntity.name)
        assertEquals(shopJsonModel.description_en, shopEntity.description_en)
        assertEquals(shopJsonModel.description_es, shopEntity.description_es)
        assertEquals(shopJsonModel.opening_hours_en, shopEntity.openingHours_en)
        assertEquals(shopJsonModel.opening_hours_es, shopEntity.openingHours_es)
        assertEquals(shopJsonModel.address, shopEntity.address)
        assertEquals(Float.NaN, shopEntity.latitude)
        assertEquals(2.0f, shopEntity.longitude, 0.1f)

    }

    @Test
    @Throws(Exception::class)
    fun given_ActivityJsonModel_Maps_To_ActivityEntityModel()
    {
        // Arrange
        val activityJsonModel = ActivityJsonModel(
                1,
                "MiActividad",
                "http://imagen.com/imagen.jpg",
                "http://imagen.com/logo.jpg",
                "De 10 a 2 en",
                "De 10 a 2 es",
                description_en = "Descripcion en",
                description_es = "Descripcion es",
                address = "Direccion",
                gps_lat = "1.0",
                gps_lon = "2.0")

        // Act

        val activityEntity = activityJsonModel.map()

        //Assert
        assertNotNull(activityEntity)
        assertEquals(activityJsonModel.id, activityEntity.id)
        assertEquals(activityJsonModel.name,activityEntity.name)
        assertEquals(activityJsonModel.description_en, activityEntity.description_en)
        assertEquals(activityJsonModel.description_es, activityEntity.description_es)
        assertEquals(activityJsonModel.opening_hours_en, activityEntity.openingHours_en)
        assertEquals(activityJsonModel.opening_hours_es, activityEntity.openingHours_es)
        assertEquals(activityJsonModel.address, activityEntity.address)
        assertEquals(1.0f, activityEntity.latitude, 0.1f)
        assertEquals(2.0f, activityEntity.longitude, 0.1f)

    }

    @Test
    @Throws(Exception::class)
    fun given_ActivityJsonModel_WithWrongLatitude_Maps_To_ActivityEntityModel()
    {
        // Arrange
        val activityJsonModel = ActivityJsonModel(
                1,
                "MiActividad",
                "http://imagen.com/imagen.jpg",
                "http://imagen.com/logo.jpg",
                "De 10 a 2 en",
                "De 10 a 2 es",
                description_en = "Descripcion en",
                description_es = "Descripcion es",
                address = "Direccion",
                gps_lat = "3499mmm",
                gps_lon = "2.0")

        // Act

        val activityEntity = activityJsonModel.map()

        //Assert
        assertNotNull(activityEntity)
        assertEquals(activityJsonModel.id, activityEntity.id)
        assertEquals(activityJsonModel.name,activityEntity.name)
        assertEquals(activityJsonModel.description_en, activityEntity.description_en)
        assertEquals(activityJsonModel.description_es, activityEntity.description_es)
        assertEquals(activityJsonModel.opening_hours_en, activityEntity.openingHours_en)
        assertEquals(activityJsonModel.opening_hours_es, activityEntity.openingHours_es)
        assertEquals(activityJsonModel.address, activityEntity.address)
        assertEquals(Float.NaN, activityEntity.latitude)
        assertEquals(2.0f, activityEntity.longitude, 0.1f)

    }
}