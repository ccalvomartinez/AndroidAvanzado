package com.calvo.carolina.madridshops.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.calvo.carolina.business.model.Shop
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
            val shop = marker.tag as Shop
            val imageView = placeView.findViewById<ImageView>(R.id.info_window_image)
            val isImageLoaded = markersAlreadyLoaded[marker.id] ?: false
            if (isImageLoaded)
            {
                Picasso.with(context)
                        .load(shop.logo)
                        .placeholder(R.drawable.tienda_ico)
                        .into(placeView.info_window_image)
            } else
            {
                markersAlreadyLoaded[marker.id] = true
                Picasso.with(context)
                        .load(shop.logo)
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

class MarkerCallback(val marker: Marker): Callback {

    override fun onSuccess()
    {
        marker.showInfoWindow();
    }

    override fun onError()
    {
        Log.e("Shops", "Error getting image")
    }

}