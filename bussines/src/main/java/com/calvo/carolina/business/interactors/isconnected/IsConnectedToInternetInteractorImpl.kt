package com.calvo.carolina.business.interactors.isconnected

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure
import java.lang.ref.WeakReference

class IsConnectedToInternetInteractorImpl(private val weakContext: WeakReference<Context>): IsConnectedToInternetInteractor
{
    @SuppressLint("MissingPermission")
    override fun execute(success: CodeClosure, errorClosure: ErrorClosure)
    {
        val isConnected: Boolean
        val connectivityManager = weakContext.get()!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try
        {
            val activeNetwork = connectivityManager.activeNetworkInfo
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting

            if (isConnected)
            {
                success()
            } else
            {
                errorClosure("No hay conexión a internet")
            }
        }
        catch (ex: SecurityException)
        {
            Log.e("Security exception", ex.localizedMessage)
            errorClosure("Security exception: " + ex.localizedMessage)
        }
    }
}