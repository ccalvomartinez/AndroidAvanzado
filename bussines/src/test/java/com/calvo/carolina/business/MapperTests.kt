package com.calvo.carolina.business

import com.calvo.carolina.business.mappers.map
import com.calvo.carolina.repository.models.ActivityEntity

import com.calvo.carolina.repository.models.ShopEntity

import org.junit.Assert
import org.junit.Test

class MappersTests
{

    @Test
    @Throws(Exception::class)
    fun given_ShopEntity_Maps_To_Shop_Spanish()
    {
        // Arrange
        val shopEntity = ShopEntity(
                1,
                1,
                "Tienda",
                "Descripcion_es",
                "descripcion_en",
                3.0f,
                2.0f,
                "",
                "",
                "op_ES",
                "op_EN",
                "")

        // Act

        val shop = shopEntity.map(true)

        //Assert
        Assert.assertNotNull(shop)
        Assert.assertEquals(shopEntity.id.toInt(), shop.id)
        Assert.assertEquals(shopEntity.name, shop.name)
        Assert.assertEquals(shopEntity.description_es, shop.description)
        Assert.assertEquals(shopEntity.openingHours_es, shop.openingHours)
        Assert.assertEquals(shopEntity.address, shop.address)
        Assert.assertEquals(shopEntity.latitude.toDouble(), shop.latitude, 0.1)
        Assert.assertEquals(shopEntity.longitude.toDouble(), shop.longitude, 0.1)

    }

    @Test
    @Throws(Exception::class)
    fun given_ShopEntity_Maps_To_Shop_English()
    {
        // Arrange
        val shopEntity = ShopEntity(
                1,
                1,
                "Tienda",
                "Descripcion_es",
                "descripcion_en",
                3.0f,
                2.0f,
                "",
                "",
                "op_ES",
                "op_EN",
                "")

        // Act

        val shop = shopEntity.map(false)

        //Assert
        Assert.assertNotNull(shop)
        Assert.assertEquals(shopEntity.id.toInt(), shop.id)
        Assert.assertEquals(shopEntity.name, shop.name)
        Assert.assertEquals(shopEntity.description_en, shop.description)
        Assert.assertEquals(shopEntity.openingHours_en, shop.openingHours)
        Assert.assertEquals(shopEntity.address, shop.address)
        Assert.assertEquals(shopEntity.latitude.toDouble(), shop.latitude, 0.1)
        Assert.assertEquals(shopEntity.longitude.toDouble(), shop.longitude, 0.1)

    }

    @Test
    @Throws(Exception::class)
    fun given_ShopEntity_WithWrongLatitude_Maps_To_Shop()
    {
        // Arrange
        val shopEntity = ShopEntity(
                1,
                1,
                "Tienda",
                "Descripcion_es",
                "descripcion_en",
                Float.NaN,
                2.0f,
                "",
                "",
                "op_ES",
                "op_EN",
                "")

        // Act

        val shop = shopEntity.map(true)

        //Assert
        //Assert
        Assert.assertNotNull(shop)
        Assert.assertEquals(shopEntity.id.toInt(), shop.id)
        Assert.assertEquals(shopEntity.name, shop.name)
        Assert.assertEquals(shopEntity.description_es, shop.description)
        Assert.assertEquals(shopEntity.openingHours_es, shop.openingHours)
        Assert.assertEquals(shopEntity.address, shop.address)
        Assert.assertEquals(shopEntity.latitude.toDouble(), shop.latitude, 0.1)
        Assert.assertEquals(shopEntity.longitude.toDouble(), shop.longitude, 0.1)

    }

    @Test
    @Throws(Exception::class)
    fun given_ActivityEntity_Maps_To_Activity_Spanish()
    {
        // Arrange
        val activityEntity = ActivityEntity(
                1,
                1,
                "Actividad",
                "Descripcion_es",
                "descripcion_en",
                3.0f,
                2.0f,
                "",
                "",
                "op_ES",
                "op_EN",
                "")

        // Act

        val activity = activityEntity.map(true)

        //Assert
        Assert.assertNotNull(activity)
        Assert.assertEquals(activityEntity.id.toInt(), activity.id)
        Assert.assertEquals(activityEntity.name, activity.name)
        Assert.assertEquals(activityEntity.description_es, activity.description)
        Assert.assertEquals(activityEntity.openingHours_es, activity.openingHours)
        Assert.assertEquals(activityEntity.address, activity.address)
        Assert.assertEquals(activityEntity.latitude.toDouble(), activity.latitude, 0.1)
        Assert.assertEquals(activityEntity.longitude.toDouble(), activity.longitude, 0.1)

    }

    @Test
    @Throws(Exception::class)
    fun given_ActivityEntity_Maps_To_Activity_English()
    {
        // Arrange
        val activityEntity = ActivityEntity(
                1,
                1,
                "Actividad",
                "Descripcion_es",
                "descripcion_en",
                3.0f,
                2.0f,
                "",
                "",
                "op_ES",
                "op_EN",
                "")

        // Act

        val activity = activityEntity.map(false)

        //Assert
        Assert.assertNotNull(activity)
        Assert.assertEquals(activityEntity.id.toInt(), activity.id)
        Assert.assertEquals(activityEntity.name, activity.name)
        Assert.assertEquals(activityEntity.description_en, activity.description)
        Assert.assertEquals(activityEntity.openingHours_en, activity.openingHours)
        Assert.assertEquals(activityEntity.address, activity.address)
        Assert.assertEquals(activityEntity.latitude.toDouble(), activity.latitude, 0.1)
        Assert.assertEquals(activityEntity.longitude.toDouble(), activity.longitude, 0.1)

    }

    @Test
    @Throws(Exception::class)
    fun given_ActivityEntity_WithWrongLatitude_Maps_To_Activity()
    {
        // Arrange
        val activityEntity = ActivityEntity(
                1,
                1,
                "Actividad",
                "Descripcion_es",
                "descripcion_en",
                Float.NaN,
                2.0f,
                "",
                "",
                "op_ES",
                "op_EN",
                "")

        // Act

        val activity = activityEntity.map(true)

        //Assert
        //Assert
        Assert.assertNotNull(activity)
        Assert.assertEquals(activityEntity.id.toInt(), activity.id)
        Assert.assertEquals(activityEntity.name, activity.name)
        Assert.assertEquals(activityEntity.description_es, activity.description)
        Assert.assertEquals(activityEntity.openingHours_es, activity.openingHours)
        Assert.assertEquals(activityEntity.address, activity.address)
        Assert.assertEquals(activityEntity.latitude.toDouble(), activity.latitude, 0.1)
        Assert.assertEquals(activityEntity.longitude.toDouble(), activity.longitude, 0.1)

    }
}