package com.calvo.carolina.business

import android.content.Context
import com.calvo.carolina.business.interactors.clearallshops.DeleteAllShops
import com.calvo.carolina.business.interactors.clearallshops.DeleteAllShopsImpl
import com.calvo.carolina.business.interactors.getallactivities.GetAllActivitiesInteractor
import com.calvo.carolina.business.interactors.getallactivities.GetAllActivitiesInteractorImpl
import com.calvo.carolina.business.interactors.getallshops.GetAllShopsInteractor
import com.calvo.carolina.business.interactors.getallshops.GetAllShopsInteractorImpl
import com.calvo.carolina.business.interactors.isconnected.IsConnectedToInternetInteractor
import com.calvo.carolina.business.interactors.isconnected.IsConnectedToInternetInteractorImpl
import java.lang.ref.WeakReference

class BusinessObjectInjector(context: Context)
{
    private val weakContext = WeakReference<Context>(context)
    fun buildGetAllShopsInteractor(): GetAllShopsInteractor
    {
        return GetAllShopsInteractorImpl(weakContext)
    }

    fun buildGetAllActivitiesInteractor(): GetAllActivitiesInteractor
    {
        return GetAllActivitiesInteractorImpl(weakContext)
    }

    fun buildClearAllShopsInteractor(): DeleteAllShops
    {
        return DeleteAllShopsImpl(weakContext)
    }

    fun buildIsConnectedToInternetInteractor(): IsConnectedToInternetInteractor
    {
        return IsConnectedToInternetInteractorImpl(weakContext)
    }
}