package com.calvo.carolina.repository

import android.os.Looper
import com.calvo.carolina.repository.cache.CacheDBImpl
import com.calvo.carolina.repository.db.dao.DAOPersistable
import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.util.AndroidMockUtil
import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.RunnableUtils
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Looper::class, CacheDBImpl::class, RunnableUtils::class)
class CacheTests
{
    // WARNING Estos test fallan aleatoriamente. Creo que es por la ejecución en segundo plano.
    // TODO ("Hacer tests de Repositorio")
    // TODO ("Hacer tests de ObjectInjector")
    @Before
    fun setUp()
    {
       AndroidMockUtil.mockMainThreadHandler()
    }

    @Test
    @Throws(Exception::class)
    fun given_deleteAllShopsSuccess_RunSuccessClosure()
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
        verify(error, never()).invoke(eq("Error deleting"))
    }

    @Test
    @Throws(Exception::class)
    fun given_deleteAllShopsError_RunErrorClosure()
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
        verify(error, times(1)).invoke(eq("Error deleting"))
    }

    @Test
    @Throws(Exception::class)
    fun given_deleteAllShopsException_RunErrorClosure()
    {
        // Arrange
        val success = mock<CodeClosure>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        given(mockShopDAO.deleteAll()).willAnswer { Exception("Error") }

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.deleteAllShops(success, error)

        // Assert
        verify(success, never()).invoke()
        verify(error, times(1)).invoke(any<String>())
    }

    @Test
    @Throws(Exception::class)
    fun given_deleteAllActivitiesSuccess_RunSuccessClosure()
    {
        // Arrange
        val success = mock<CodeClosure>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        whenever(mockActivityDAO.deleteAll()).thenReturn(true)

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.deleteAllActivities(success, error)

        // Assert
        verify(success, times(1)).invoke()
        verify(error, never()).invoke(eq("Error deleting"))
    }

    @Test
    @Throws(Exception::class)
    fun given_deleteAllActivitiesError_RunErrorClosure()
    {
        // Arrange
        val success = mock<CodeClosure>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        whenever(mockActivityDAO.deleteAll()).thenReturn(false)

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.deleteAllActivities(success, error)

        // Assert
        verify(success, never()).invoke()
        verify(error, times(1)).invoke(eq("Error deleting"))
    }

    @Test
    @Throws(Exception::class)
    fun given_deleteAllActivitiesException_RunErrorClosure()
    {
        // Arrange
        val success = mock<CodeClosure>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        given(mockActivityDAO.deleteAll()).willAnswer { Exception("Error")}

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.deleteAllActivities(success, error)

        // Assert
        verify(success, never()).invoke()
        verify(error, times(1)).invoke(any<String>())
    }

    @Test
    @Throws(Exception::class)
    fun given_getAllActivitiesSuccess_RunSuccessClosure()
    {
        // Arrange
        val success = mock<(List<ActivityEntity>) -> Unit>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        val activityList = listOf(getActivityEntity())
        whenever(mockActivityDAO.query()).thenReturn(activityList)

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.getAllActivities(success, error)

        // Assert
        verify(success, times(1)).invoke(eq(activityList))
        verify(error, never()).invoke(eq("No activities"))
    }

    @Test
    @Throws(Exception::class)
    fun given_getAllActivitiesEmptyList_RunErrorClosure()
    {
        // Arrange
        val success = mock<(List<ActivityEntity>) -> Unit>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        val activityList = listOf<ActivityEntity>()
        whenever(mockActivityDAO.query()).thenReturn(activityList)

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.getAllActivities(success, error)

        // Assert
        verify(success, never()).invoke(eq(activityList))
        verify(error, times(1)).invoke(eq("No activities"))
    }

    @Test
    @Throws(Exception::class)
    fun given_getAllActivitiesException_RunErrorClosure()
    {
        // Arrange
        val success = mock<(List<ActivityEntity>) -> Unit>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        val activityList = listOf<ActivityEntity>()
        given(mockActivityDAO.query()).willAnswer { Exception("Error") }

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.getAllActivities(success, error)

        // Assert
        verify(success, never()).invoke(eq(activityList))
        verify(error, times(1)).invoke(any<String>())
    }

    @Test
    @Throws(Exception::class)
    fun given_getAllShopsSuccess_RunSuccessClosure()
    {
        // Arrange
        val success = mock<(List<ShopEntity>) -> Unit>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        val shopList = listOf(getShopEntity())
        whenever(mockShopDAO.query()).thenReturn(shopList)

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.getAllShops(success, error)

        // Assert
        verify(success, times(1)).invoke(eq(shopList))
        verify(error, never()).invoke(eq("No shops"))
    }

    @Test
    @Throws(Exception::class)
    fun given_getAllShopsEmptyList_RunErrorClosure()
    {
        // Arrange
        val success = mock<(List<ShopEntity>) -> Unit>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        val shopList = listOf<ShopEntity>()
        whenever(mockShopDAO.query()).thenReturn(shopList)

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.getAllShops(success, error)

        // Assert
        verify(success, never()).invoke(eq(shopList))
        verify(error, times(1)).invoke(eq("No shops"))
    }

    @Test
    @Throws(Exception::class)
    fun given_getAllShopsException_RunErrorClosure()
    {
        // Arrange
        val success = mock<(List<ShopEntity>) -> Unit>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        val shopList = listOf<ShopEntity>()
        given(mockShopDAO.query()).willAnswer { Exception("Error") }

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.getAllShops(success, error)

        // Assert
        verify(success, never()).invoke(eq(shopList))
        verify(error, times(1)).invoke(any<String>())
    }

    @Test
    @Throws(Exception::class)
    fun given_saveAllShopsSuccess_RunSuccessClosure()
    {
        // Arrange
        val success = mock<CodeClosure>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        val shopList = listOf<ShopEntity>(getShopEntity())
        whenever(mockShopDAO.insert(shopList)).thenReturn(true)

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.saveAllShops(shopList, success, error)

        // Assert
        verify(success, times(1)).invoke()
        verify(error, never()).invoke(eq("Error while inserting shops"))
    }

    @Test
    @Throws(Exception::class)
    fun given_saveAllShopsError_RunErrorClosure()
    {
        // Arrange
        val success = mock<CodeClosure>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        val shopList = listOf<ShopEntity>(getShopEntity())
        whenever(mockShopDAO.insert(shopList)).thenReturn(false)

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.saveAllShops(shopList, success, error)

        // Assert
        verify(success, never()).invoke()
        verify(error, times(1)).invoke(eq("Error while inserting shops"))
    }

    @Test
    @Throws(Exception::class)
    fun given_saveAllShopsException_RunErrorClosure()
    {
        // Arrange
        val success = mock<CodeClosure>()
        val error = mock<ErrorClosure>()
        val mockShopDAO = mock<DAOPersistable<ShopEntity>>()
        val mockActivityDAO = mock<DAOPersistable<ActivityEntity>>()
        val shopList = listOf<ShopEntity>(getShopEntity())
        given(mockShopDAO.insert(shopList)).willAnswer { Exception("Error") }

        val cache = CacheDBImpl(mockShopDAO, mockActivityDAO)
        //Act
        cache.saveAllShops(shopList, success, error)

        // Assert
        verify(success, never()).invoke()
        verify(error, times(1)).invoke(any<String>())
    }
    
    private fun getActivityEntity(): ActivityEntity
    {
        return ActivityEntity(
                1,
                1,
                "Actividad",
                "Descripcion_es",
                "descripcion_en",
                3.0f,
                2.0f,
                "",
                "",
                "",
                "",
                "")
    }

    private fun getShopEntity(): ShopEntity
    {
        return ShopEntity(
                1,
                1,
                "Actividad",
                "Descripcion_es",
                "descripcion_en",
                3.0f,
                2.0f,
                "",
                "",
                "",
                "",
                "")
    }
}
