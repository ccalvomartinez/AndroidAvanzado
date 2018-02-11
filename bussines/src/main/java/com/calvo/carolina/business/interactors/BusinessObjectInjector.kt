package com.calvo.carolina.business.interactors

import android.content.Context
import com.calvo.carolina.business.interactors.clearallshops.DeleteAllShops
import com.calvo.carolina.business.interactors.clearallshops.DeleteAllShopsImpl
import com.calvo.carolina.business.interactors.getallshops.GetAllShopsInteractor
import com.calvo.carolina.business.interactors.getallshops.GetAllShopsInteractorImpl
import com.calvo.carolina.business.interactors.isconnected.IsConnectedToInternetInteractor
import com.calvo.carolina.business.interactors.isconnected.IsConnectedToInternetInteractorFakeImpl
import java.lang.ref.WeakReference

class BusinessObjectInjector(context: Context)
{
    val weakContext = WeakReference<Context>(context)
    fun BuildGetAllShopsInteractor(): GetAllShopsInteractor
    {
        return GetAllShopsInteractorImpl(weakContext)
    }

    fun BuildClearAllShopsInteractor(): DeleteAllShops
    {
        return DeleteAllShopsImpl(weakContext)
    }

    internal fun BuildIsConnectedToInternetInteractor(): IsConnectedToInternetInteractor
    {
        return IsConnectedToInternetInteractorFakeImpl()
    }
}