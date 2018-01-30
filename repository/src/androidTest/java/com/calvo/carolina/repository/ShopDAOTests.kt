package com.calvo.carolina.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.calvo.carolina.repository.db.buildDBHelper
import com.calvo.carolina.repository.db.dao.ShopDAO
import com.calvo.carolina.repository.db.models.ShopDAOModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

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
    val dbHelper = buildDBHelper(appContext, "mydb.sqlite", 1)

    @Test
    @Throws(Exception::class)
    fun testInsert_given_valid_shop_it_gets_inserted()
    {
        // Arrange
        val shop = ShopDAOModel(1, 1, "MyShop", "", 1.0F, 2.0F, "", "", "", "")
        val shopDAO = ShopDAO(dbHelper)

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
        val shop = ShopDAOModel(1, 1, "MyShop", "", 1.0F, 2.0F, "", "", "", "")
        val shopDAO = ShopDAO(dbHelper)

        val id = shopDAO.insert(shop)
        assertTrue("El id tiene que ser mayor que 0", id > 0)

        // Act

        val result = shopDAO.deleteAll()

        // Assert
        assertTrue("Tiene que devolver TRUE porque hay registros", result)
    }

    @Test
    @Throws(Exception::class)
    fun testInsert_given_two_rows_inserted_ignore_databaseID()
    {
        // Arrange
        val shop = ShopDAOModel(1, 1, "MyShop", "", 1.0F, 2.0F, "", "", "", "")
        val shop2 = ShopDAOModel(2, 1, "MyShop2", "", 1.0F, 2.0F, "", "", "", "")
        val shopDAO = ShopDAO(dbHelper)

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
        val shopDAO = ShopDAO(dbHelper)
        shopDAO.deleteAll()
        val shop = ShopDAOModel(1, 1, "MyShop", "", 1.0F, 2.0F, "", "", "", "")
        val shop2 = ShopDAOModel(2, 1, "MyShop2", "", 1.0F, 2.0F, "", "", "", "")
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
        val shopDAO = ShopDAO(dbHelper)
        shopDAO.deleteAll()
        val shop = ShopDAOModel(1, 1, "MyShop", "", 1.0F, 2.0F, "", "", "", "")
        val databaseID = shopDAO.insert(shop)

        // Act
        val result = shopDAO.query(databaseID)
        // Assert
        assertTrue(result.databaseID == databaseID)
        assertTrue(result.name == shop.name)
        assertTrue(result.latitude == shop.latitude)
    }
}
