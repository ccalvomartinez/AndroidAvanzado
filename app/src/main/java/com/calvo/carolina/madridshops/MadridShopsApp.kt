package com.calvo.carolina.madridshops

import com.calvo.carolina.bussines.interactors.*
import com.calvo.carolina.bussines.model.*
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.calvo.carolina.bussines.interactors.getallshops.GetAllShopsInteractorFake


class MadridShopsApp: MultiDexApplication()
{
    override fun onCreate()
    {
        // Se lanza cuando se crea la aplicación
        // Es el lugar adecuado para inicializar los elementos necesarios para el funcionamiento de la aplicación
        super.onCreate()
        Log.d("MadridShops", "onCreate")
       val allShopsInteractor = GetAllShopsInteractorFake()
        allShopsInteractor.execute(object: SuccessCompletion<Shops>
        {
            override fun successCompletion(e: Shops)
            {
                Log.d("MadridShops", "Shop count" + e.count())
            }

        }, object : ErrorCompletion
        {
            override fun errorCompletion(errorMessage: String)
            {
                Log.d("MadridShops", errorMessage)
            }
        })

        allShopsInteractor.execute(
                { shops: Shops ->

                },
                { msg: String ->

                }
        )
    }
    override fun onLowMemory()
    {
        super.onLowMemory()
    }
}