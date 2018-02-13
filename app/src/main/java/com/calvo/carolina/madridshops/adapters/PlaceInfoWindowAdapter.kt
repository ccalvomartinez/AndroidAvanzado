package com.calvo.carolina.madridshops.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.calvo.carolina.business.model.Place
import com.calvo.carolina.madridshops.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.info_window_pin.view.*
import java.util.*

class PlaceInfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter
{
    private val markersAlreadyLoaded = Hashtable<String, Boolean>()
    private val placeView = LayoutInflater.from(context).inflate(R.layout.info_window_pin, null)

    override fun getInfoContents(marker: Marker?): View
    {
        placeView.findViewById<TextView>(R.id.info_window_title).text = marker?.title
        if (marker != null){
            val place = marker.tag as Place
            val isImageLoaded = markersAlreadyLoaded[marker.id] ?: false
            placeView.info_window_image.contentDescription = place.name
            if (isImageLoaded)
            {
                Picasso.with(context)
                        .load(place.logo)
                        .placeholder(R.drawable.tienda_ico)
                        .into(placeView.info_window_image)
            } else
            {
                markersAlreadyLoaded[marker.id] = true
                Picasso.with(context)
                        .load(place.logo)
                        .placeholder(R.drawable.tienda_ico)
                        .into(placeView.info_window_image, MarkerCallback(marker))
            }

        }
        return placeView
    }

    override fun getInfoWindow(marker: Marker?): View?
    {
        return null
    }
}

class MarkerCallback(private val marker: Marker): Callback {

    override fun onSuccess()
    {
        marker.showInfoWindow()
    }

    override fun onError()
    {
        Log.e("MadridShops", "Error getting image")
    }

}