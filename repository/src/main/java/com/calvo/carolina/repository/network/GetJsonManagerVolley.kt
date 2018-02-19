package com.calvo.carolina.repository.network

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.SuccessClosure
import java.lang.ref.WeakReference

internal class GetJsonManagerVolley(private val weakContext: WeakReference<Context>): GetJsonManager
{
    // Actividad -> (Strong) Interactor ->(Strong)  Repository ->(Strong)  Volley ->(Strong)  Activity-Context
    // Tenemos un ciclo de punteros strong y eso puede causar pérdida de memoria al no poder eliminarse la actividad cuando pulsamos atrás
    // Entonces el puntero al contexto debe ser weak
    private val requestQueue: RequestQueue by lazy {
          Volley.newRequestQueue(weakContext.get())
    }

    override fun execute(url: String, success: SuccessClosure<String>, error: ErrorClosure)
    {
       // Create request (success, failure)
        val request = StringRequest( url,
                Response.Listener
                {
                    success(it)
                },
                Response.ErrorListener {
                    error(it.localizedMessage)
                })
        // Add request to queue
        requestQueue.add(request)
    }


}


