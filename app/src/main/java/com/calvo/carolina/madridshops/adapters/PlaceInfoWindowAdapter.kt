package com.calvo.carolina.madridshops.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.calvo.carolina.madridshops.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class PlaceInfoWindowAdapter(context: Context) : GoogleMap.InfoWindowAdapter
{
    private val placeView = LayoutInflater.from(context).inflate(R.layout.info_window_pin, null)
    override fun getInfoContents(marker: Marker?): View
    {
        placeView.findViewById<TextView>(R.id.info_window_title).text = marker?.title
        // TODO("Mostrar imagen")
        placeView.findViewById<TextView>(R.id.info_window_title2).text = marker?.title
        return placeView
    }

    override fun getInfoWindow(marker: Marker?): View?
    {
        return null
    }
}