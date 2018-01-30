package com.calvo.carolina.madridshops


import com.calvo.carolina.business.model.*
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.calvo.carolina.business.interactors.getallshops.*


class MadridShopsApp: MultiDexApplication()
{
    override fun onCreate()
    {
        // Se lanza cuando se crea la aplicación
        // Es el lugar adecuado para inicializar los elementos necesarios para el funcionamiento de la aplicación
        super.onCreate()
        Log.d("MadridShops", "onCreate")
       val allShopsInteractor = GetAllShopsInteractorFake()
        allShopsInteractor.execute(
        { shops: Shops ->
                Log.d("MadridShops", "Shop count" + shops.count())
        }, { errorMessage: String ->
                Log.d("MadridShops", errorMessage)
        })

    }
    override fun onLowMemory()
    {
        super.onLowMemory()
    }
}