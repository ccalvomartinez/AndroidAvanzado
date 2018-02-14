package com.calvo.carolina.repository

import android.os.Looper
import com.calvo.carolina.repository.cache.CacheDBImpl
import com.calvo.carolina.repository.db.dao.DAOPersistable
import com.calvo.carolina.repository.models.ActivityEntity
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

@RunWith(PowerMockRunner::class)
@PrepareForTest(Looper::class, CacheDBImpl::class)
class CacheTests
{
    // TODO ("Hacer tests de actividades")
    // TODO ("Hacer tests de Repositorio")
    // TODO ("Hacer tests de ObjectInjector")
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
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        whenever(mockShopDAO.deleteAll()).thenReturn(true)

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
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
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        whenever(mockShopDAO.deleteAll()).thenReturn(false)

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.deleteAllShops(success, error)

        // Assert
        verify(success, never()).invoke()
        verify(error, times(1)).invoke(any<String>())
    }


}
