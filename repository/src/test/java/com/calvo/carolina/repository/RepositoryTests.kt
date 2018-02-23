package com.calvo.carolina.repository

import com.calvo.carolina.repository.cache.Cache
import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.network.GetJsonManager
import com.calvo.carolina.repository.network.json.model.ActivityJsonModel
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.SuccessClosure
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

typealias ListClosure = SuccessClosure<List<ActivityEntity>>

@Suppress("UNCHECKED_CAST")
@RunWith(PowerMockRunner::class)
@PrepareForTest(RepositoryImpl::class)
class RepositoryTests
{
    @Test
    @Throws(Exception::class)
    fun given_getAllActivitiesSuccess_ExecuteSuccessClosure()
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

    // Soy incapaz de hacer que funcione este test


//    @Test
//    @Throws(Exception::class)
//    fun given_getAllActivitiesEmptyListAndSuccesPopulateCache_ExecuteSuccesClosure()
//    {
//        val mockCache = mock<Cache>()
//        val success = mock<ListClosure>()
//        val error = mock<ErrorClosure>()
//        whenever(mockCache.getAllActivities(any<ListClosure>(), any<ErrorClosure>())).then {
//
//            (it.arguments[1] as  ErrorClosure).invoke("Empty list")
//
//        }
//
//        val mockJsonManager = mock<GetJsonManager>()
//        val returnedJsonString = "JsonString"
//        val activitiesList = ListActivitiesJsonEntity(listOf(getActivityJsonModel()))
//        whenever(mockJsonManager.execute(any<String>(),any<SuccessClosure<String>>(), any<ErrorClosure>()))
//                .then {
//                    (it.arguments[0] as  SuccessClosure<String>).invoke(returnedJsonString)
//                }
//        val mockParser = PowerMockito.mock(JsonEntitiesParser::class.java)
//        PowerMockito.whenNew(JsonEntitiesParser::class.java).withNoArguments().thenReturn(mockParser)
//        PowerMockito.`when`(mockParser.parse<ListActivitiesJsonEntity>(returnedJsonString)).thenReturn(activitiesList)
//
//        whenever(mockCache.saveAllActivities(any<List<ActivityEntity>>(),any<CodeClosure>(), any<ErrorClosure>())).then {
//
//            (it.arguments[0] as  CodeClosure).invoke()
//
//        }
//        val repositoryImpl = RepositoryImpl(mockCache, mockJsonManager)
//
//        // Act
//        repositoryImpl.getAllActivities(success, error)
//
//        // Assert
//        verify(mockCache, times(1)).getAllActivities(any<ListClosure>(), any<ErrorClosure>())
//        verify(success, times(1)).invoke(any<List<ActivityEntity>>())
//        verify(error, never()).invoke(any<String>())
//    }

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

    private fun getActivityJsonModel(): ActivityJsonModel
    {
        return ActivityJsonModel(
                1,
                "Activity1",
                "",
                "",
                "",
                "",
                gps_lon = "",
                gps_lat = "")
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