package com.calvo.carolina.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.calvo.carolina.repository.db.buildDBHelper
import com.calvo.carolina.repository.db.dao.ShopDAO
import com.calvo.carolina.repository.db.models.ShopDAOModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest
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
        assertTrue("El id tiene que ser mayor que ", id > 0)
    }
}
