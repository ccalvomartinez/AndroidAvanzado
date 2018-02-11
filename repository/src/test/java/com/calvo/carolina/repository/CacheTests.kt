package com.calvo.carolina.repository

import android.content.Context
import android.database.Cursor
import android.os.Looper
import android.test.mock.MockContext
import com.calvo.carolina.repository.cache.CacheDBImpl
import com.calvo.carolina.repository.db.dao.DAOPersistable
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.util.AndroidMockUtil
import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure

import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

import java.lang.ref.WeakReference

@RunWith(PowerMockRunner::class)
@PrepareForTest(Looper::class, CacheDBImpl::class)
class CacheTests
{
    @Before
    fun setUp()
    {
        AndroidMockUtil.mockMainThreadHandler()
    }


    @Test
    @Throws(Exception::class)
    fun convert_given_deleteAllShopsSucces_RunSuccesClosure()
    {
        // Arrange
        val success = mock<CodeClosure>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        whenever(mockShopDAO.deleteAll()).thenReturn(true)
        val mockContext = mock<WeakReference<Context>>()

        val cache = CacheDBImpl(mockContext, mockShopDAO)
        //Act
        cache.deleteAllShops(success, error)

        // Assert
        verify(success, times(1)).invoke()
        verify(error, never()).invoke(any<String>())
    }
    @Test
    @Throws(Exception::class)
    fun convert_given_deleteAllShopsError_RunErrorClosure()
    {
        // Arrange
        val success = mock<CodeClosure>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        whenever(mockShopDAO.deleteAll()).thenReturn(false)
        val mockContext = mock<WeakReference<Context>>()

        val cache = CacheDBImpl(mockContext, mockShopDAO)
        //Act
        cache.deleteAllShops(success, error)

        // Assert
        verify(success, never()).invoke()
        verify(error, times(1)).invoke(any<String>())
    }


}



class SuccesMockShopDAO: DAOPersistable<ShopEntity>
{
    override fun insert(element: ShopEntity): Long
    {
        return 1
    }

    override fun update(databaseID: Long, element: ShopEntity): Long
    {
        return 1
    }

    override fun delete(element: ShopEntity): Long
    {
        return 1
    }

    override fun delete(databaseID: Long): Long
    {
        return 1
    }

    override fun deleteAll(): Boolean
    {
        return true
    }

    override fun query(databaseID: Long): ShopEntity?
    {
        return ShopEntity(1, 1, "MyShop","", 1.0f, 2.0f, "", "", "", "")
    }

    override fun query(): List<ShopEntity>
    {
       return arrayListOf()
    }

    override fun queryCursor(databaseID: Long): Cursor
    {
        throw UnsupportedOperationException()
    }

}