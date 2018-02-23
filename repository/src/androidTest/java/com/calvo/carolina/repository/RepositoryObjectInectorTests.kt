package com.calvo.carolina.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.calvo.carolina.repository.cache.CacheDBImpl
import com.calvo.carolina.repository.db.dao.ActivityDAO
import com.calvo.carolina.repository.db.dao.ShopDAO
import com.calvo.carolina.repository.network.GetJsonManagerVolley
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.ref.WeakReference

@RunWith(AndroidJUnit4::class)
class RepositoryObjectInectorTests
{

    val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    @Throws(Exception::class)
    fun given_buildGetJsonManager_returnGetJsonManagerVolley()
    {
        // Arrange
        val objectInjector = RepositoryObjectInjector(WeakReference(appContext))
        // Act
        val jsonManager = objectInjector.buildGetJsonManager()

        // Assert
        assert(jsonManager is GetJsonManagerVolley)
    }

    @Test
    @Throws(Exception::class)
    fun given_buildDAOPersistableActivities_returnActivityDAO()
    {
        // Arrange
        val objectInjector = RepositoryObjectInjector(WeakReference(appContext))
        // Act
        val daoPersistable = objectInjector.buildDAOPersistableActivities()

        // Assert
        assert(daoPersistable is ActivityDAO)
    }

    @Test
    @Throws(Exception::class)
    fun given_buildDAOPersistableShops_returnShopDAO()
    {
        // Arrange
        val objectInjector = RepositoryObjectInjector(WeakReference(appContext))
        // Act
        val daoPersistable = objectInjector.buildDAOPersistableShops()

        // Assert
        assert(daoPersistable is ShopDAO)
    }

    @Test
    @Throws(Exception::class)
    fun given_buildCache_returnCacheDBImpl()
    {
        // Arrange
        val objectInjector = RepositoryObjectInjector(WeakReference(appContext))

        // Act
        val cache = objectInjector.buildCache()

        // Assert
        assert(cache is CacheDBImpl)
    }

    @Test
    @Throws(Exception::class)
    fun given_buildRepository_returnRepositoryImpl()
    {
        // Arrange
        val objectInjector = RepositoryObjectInjector(WeakReference(appContext))
        // Act
        val repository = objectInjector.buildRepository()

        // Assert
        assert(repository is RepositoryImpl)
    }
}