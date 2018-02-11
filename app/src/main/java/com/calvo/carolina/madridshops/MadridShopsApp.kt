package com.calvo.carolina.madridshops


import com.calvo.carolina.business.model.*
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.calvo.carolina.business.interactors.BusinessObjectInjector


class MadridShopsApp: MultiDexApplication()
{
    private  val businessInjector = BusinessObjectInjector(this)
    override fun onCreate()
    {
        // Se lanza cuando se crea la aplicación
        // Es el lugar adecuado para inicializar los elementos necesarios para el funcionamiento de la aplicación
        super.onCreate()
        Log.d("MadridShops", "onCreate")
    }
    override fun onLowMemory()
    {
        super.onLowMemory()
    }
}