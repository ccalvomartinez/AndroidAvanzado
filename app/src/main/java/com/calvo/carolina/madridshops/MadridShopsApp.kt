package com.calvo.carolina.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.squareup.picasso.Picasso


class MadridShopsApp: MultiDexApplication()
{
    override fun onCreate()
    {
        // Se lanza cuando se crea la aplicación
        // Es el lugar adecuado para inicializar los elementos necesarios para el funcionamiento de la aplicación
        super.onCreate()
        Log.d("MadridShops", "onCreate")
        Picasso.with(this).setIndicatorsEnabled(true)
        Picasso.with(this).isLoggingEnabled = true
    }

}