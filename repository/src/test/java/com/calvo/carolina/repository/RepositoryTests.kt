package com.calvo.carolina.repository

import com.calvo.carolina.repository.cache.Cache
import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.network.GetJsonManager
import com.calvo.carolina.util.ErrorClosure
import com.nhaarman.mockito_kotlin.*
import org.junit.Test

typealias ListClosure = (List<ActivityEntity>) -> Unit
class RepositoryTests
{
    @Test
    @Throws(Exception::class)
    fun test()
    {
        val mockCache = mock<Cache>()
        val activitiesList = listOf<ActivityEntity>(getActivityEntity())
        val success = mock<ListClosure>()
        val error = mock<ErrorClosure>()
        whenever(mockCache.getAllActivities(any<ListClosure>(), any<ErrorClosure>())).then {

            (it.arguments[0] as  ListClosure).invoke(activitiesList)

        }
        val mockJsonManager = mock<GetJsonManager>()
        val repositoryImpl = RepositoryImpl(mockCache, mockJsonManager)

        // Act
        repositoryImpl.getAllActivities(success, error)

        // Assert
        verify(mockCache, times(1)).getAllActivities(any<ListClosure>(), any<ErrorClosure>())
        verify(success, times(1)).invoke(eq(activitiesList))
        verify(error, never()).invoke(any<String>())
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