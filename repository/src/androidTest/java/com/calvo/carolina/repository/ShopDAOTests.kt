package com.calvo.carolina.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.util.RepositoryObjectInjector

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.lang.ref.WeakReference

/**
 * ----------------------------------------------------------------------------------
 *   OJO CUIDAO!! Para que funcionen los tests hay que crear una congiguración de RUN
 *   Run -> Edit Configurations -> + -> Android Instrumented Tests
 * ----------------------------------------------------------------------------------
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ShopDAOTests
{
    val appContext = InstrumentationRegistry.getTargetContext()
    val objectInjector = RepositoryObjectInjector(WeakReference<Context>(appContext))

    @Test
    @Throws(Exception::class)
    fun testInsert_given_valid_shop_it_gets_inserted()
    {
        // Arrange
        val shop = ShopEntity(1, 1, "MyShop", "", "",1.0F, 2.0F, "", "", "", "","")
        val shopDAO =objectInjector.BuildShopDAO()

        // Act
        val id = shopDAO.insert(shop)

        // Assert
        assertTrue("El id tiene que ser mayor que 0", id > 0)
    }

    @Test
    @Throws(Exception::class)
    fun testDeleteAll_given_rows_in_database_returns_true()
    {
        // Arrange
        val shop = ShopEntity(1, 1, "MyShop", "", "",1.0F, 2.0F, "", "", "", "","")

        val shopDAO = objectInjector.BuildShopDAO()

        val id = shopDAO.insert(shop)
        assertTrue("El id tiene que ser mayor que 0", id > 0)

        // Act

        val result = shopDAO.deleteAll()

        // Assert
        assertTrue("Tiene que devolver TRUE", result)
    }

    @Test
    @Throws(Exception::class)
    fun testDeleteAll_given_no_rows_in_database_returns_true()
    {
        // Arrange
        val shopDAO = objectInjector.BuildShopDAO()
        shopDAO.deleteAll()
        // Act

        val result = shopDAO.deleteAll()

        // Assert
        assertTrue("Tiene que devolver TRUE", result)
    }

    @Test
    @Throws(Exception::class)
    fun testInsert_given_two_rows_inserted_ignore_databaseID()
    {
        // Arrange
        val shop = ShopEntity(1, 1, "MyShop", "", "",1.0F, 2.0F, "", "", "", "","")
        val shop2 = ShopEntity(2, 1, "MyShop", "", "",1.0F, 2.0F, "", "", "", "","")
        val shopDAO = objectInjector.BuildShopDAO()

        // Act
        val id = shopDAO.insert(shop)
        val id2 = shopDAO.insert(shop2)

        // Assert
        assertTrue("Los ids devueltos deben ser distintos", id != id2)
    }

    @Test
    @Throws(Exception::class)
    fun testQuery_given_two_rows_inserted_return_two_elements()
    {
        // Arrange
        val shopDAO = objectInjector.BuildShopDAO()
        shopDAO.deleteAll()
        val shop = ShopEntity(1, 1, "MyShop", "", "",1.0F, 2.0F, "", "", "", "","")
        val shop2 = ShopEntity(1, 2, "MyShop", "", "",1.0F, 2.0F, "", "", "", "","")

        shopDAO.insert(shop)
        shopDAO.insert(shop2)

        // Act
        val result = shopDAO.query()
        // Assert
        assertTrue("Los ids devueltos deben ser distintos", result.size == 2)
    }

    @Test
    @Throws(Exception::class)
    fun testQueryByID_given_row_inserted_return_correct_element()
    {
        // Arrange
        val shopDAO = objectInjector.BuildShopDAO()
        shopDAO.deleteAll()
        val shop = ShopEntity(1, 1, "MyShop", "", "",1.0F, 2.0F, "", "", "", "","")

        val databaseID = shopDAO.insert(shop)

        // Act
        val result = shopDAO.query(databaseID)
        // Assert
        assertNotNull(result)
        val shopResult = result!!
        assertTrue(shopResult.databaseID == databaseID)
        assertTrue(shopResult.name == shop.name)
        assertTrue(shopResult.latitude == shop.latitude)
    }

    @Test
    @Throws(Exception::class)
    fun testQueryByID_given_databaseIDNotExists_return_null()
    {
        // Arrange
        val shopDAO = objectInjector.BuildShopDAO()
        shopDAO.deleteAll()

        // Act
        val result = shopDAO.query(34)

        // Assert
        assertNull(result)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdate_given_row_updates_return_correct_element()
    {
        // Arrange
        val shopDAO = objectInjector.BuildShopDAO()
        shopDAO.deleteAll()
        val shop = ShopEntity(1, 1, "MyShop", "", "",1.0F, 2.0F, "", "", "", "","")

        val databaseID = shopDAO.insert(shop)

        val shop2 = ShopEntity(2, 1, "MyShop2", "", "",2.0F, 2.0F, "", "", "", "","")

        // Act
        val numberOfRegistersAffected = shopDAO.update(databaseID, shop2)
        val registerUpdated = shopDAO.query(databaseID)

        // Assert
        assertTrue(numberOfRegistersAffected == 1.toLong())
        assertNotNull(registerUpdated)
        val shopUpdated = registerUpdated!!
        assertTrue(shopUpdated.databaseID == databaseID)
        assertTrue(shopUpdated.name == shop2.name)
        assertTrue(shopUpdated.latitude == shop2.latitude)
    }
}
