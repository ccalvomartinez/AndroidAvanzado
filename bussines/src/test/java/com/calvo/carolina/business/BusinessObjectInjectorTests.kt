package com.calvo.carolina.business


import android.test.mock.MockContext
import com.calvo.carolina.business.interactors.getallactivities.GetAllActivitiesInteractorImpl
import com.calvo.carolina.business.interactors.getallshops.GetAllShopsInteractorImpl
import com.calvo.carolina.repository.Repository
import com.calvo.carolina.repository.RepositoryObjectInjector
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */

@RunWith(PowerMockRunner::class)
@PrepareForTest(GetAllActivitiesInteractorImpl::class, GetAllShopsInteractorImpl::class)
class BusinessObjectInjectorTests
{
    @Test
    @Throws(Exception::class)
    fun given_buildGetAllActivitiesInteractor_return_GetAllActivitiesInteractorImpl()
    {
        // Arrange
        val mockContext = MockContext()
        val objectInjector = BusinessObjectInjector(mockContext)
        val mockRepositoryOI = PowerMockito.mock(RepositoryObjectInjector::class.java)
        PowerMockito.whenNew(RepositoryObjectInjector::class.java).withAnyArguments().thenReturn(mockRepositoryOI)
        PowerMockito.`when`(mockRepositoryOI.buildRepository()).thenReturn(PowerMockito.mock(Repository::class.java))

        // Act
        val interactor = objectInjector.buildGetAllActivitiesInteractor()

        // Assert

        assert(interactor is GetAllActivitiesInteractorImpl)
    }

    @Test
    @Throws(Exception::class)
    fun given_buildGetAllShopsInteractor_return_GetAllShopsInteractorImpl()
    {
        // Arrange
        val mockContext = MockContext()
        val objectInjector = BusinessObjectInjector(mockContext)
        val mockRepositoryOI = PowerMockito.mock(RepositoryObjectInjector::class.java)
        PowerMockito.whenNew(RepositoryObjectInjector::class.java).withAnyArguments().thenReturn(mockRepositoryOI)
        PowerMockito.`when`(mockRepositoryOI.buildRepository()).thenReturn(PowerMockito.mock(Repository::class.java))

        // Act
        val interactor = objectInjector.buildGetAllShopsInteractor()

        // Assert

        assert(interactor is GetAllShopsInteractorImpl)
    }
}