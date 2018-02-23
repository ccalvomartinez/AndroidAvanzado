package com.calvo.carolina.business


import android.test.mock.MockContext
import com.calvo.carolina.business.interactors.getallactivities.GetAllActivitiesInteractorImpl
import com.calvo.carolina.business.interactors.getallshops.GetAllShopsInteractorImpl
import com.calvo.carolina.business.model.Places
import com.calvo.carolina.repository.Repository
import com.calvo.carolina.repository.RepositoryObjectInjector
import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.SuccessClosure
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import java.lang.ref.WeakReference

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */

@RunWith(PowerMockRunner::class)
@PrepareForTest(GetAllActivitiesInteractorImpl::class, GetAllShopsInteractorImpl::class)
@Suppress("UNCHECKED_CAST")
class InteractorsTests
{
    @Test
    @Throws(Exception::class)
    fun given_GetAllActivitiesInteractorImplExecuteError_ExecuteErrorClosure()
    {
        // Arrange
        val mockContext = MockContext()
         val success = mock<SuccessClosure<Places>>()
        val error = mock<ErrorClosure>()
        val mockRepositoryOI = PowerMockito.mock(RepositoryObjectInjector::class.java)
        val mockRepository = PowerMockito.mock(Repository::class.java)

        PowerMockito.`when`(mockRepository.getAllActivities(any<SuccessClosure<List<ActivityEntity>>>(),any<ErrorClosure>()))
                .then {
                    (it.arguments[1] as  ErrorClosure).invoke("Error")
                }


        PowerMockito.whenNew(RepositoryObjectInjector::class.java).withAnyArguments().thenReturn(mockRepositoryOI)
        PowerMockito.`when`(mockRepositoryOI.buildRepository()).thenReturn(mockRepository)


        val interactor = GetAllActivitiesInteractorImpl(WeakReference(mockContext))

        // Act
         interactor.execute(true, success, error)

        // Assert

        // Assert
        verify(success, never()).invoke(any())
        verify(error, times(1)).invoke(eq("Error"))
    }

    @Test
    @Throws(Exception::class)
    fun given_GetAllActivitiesInteractorImplExecuteSuccess_ExecuteSuccessClosure()
    {
        // Arrange
        val mockContext = MockContext()
        val success = mock<SuccessClosure<Places>>()
        val error = mock<ErrorClosure>()
        val mockRepositoryOI = PowerMockito.mock(RepositoryObjectInjector::class.java)
        val mockRepository = PowerMockito.mock(Repository::class.java)
        val activityList = listOf(getActivityEntity())
        PowerMockito.`when`(mockRepository.getAllActivities(any<SuccessClosure<List<ActivityEntity>>>(),any<ErrorClosure>()))
                .then {
                    (it.arguments[0] as  SuccessClosure<List<ActivityEntity>>).invoke(activityList)
                }


        PowerMockito.whenNew(RepositoryObjectInjector::class.java).withAnyArguments().thenReturn(mockRepositoryOI)
        PowerMockito.`when`(mockRepositoryOI.buildRepository()).thenReturn(mockRepository)


        val interactor = GetAllActivitiesInteractorImpl(WeakReference(mockContext))

        // Act
        interactor.execute(true, success, error)

        // Assert

        // Assert
        verify(success, times(1)).invoke(any())
        verify(error, never()).invoke(any())
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