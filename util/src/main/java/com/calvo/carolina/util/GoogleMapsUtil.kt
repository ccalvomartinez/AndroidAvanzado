package com.calvo.carolina.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
class GoogleMapsUtil
{
    companion object
    {
        fun initializeMap(mapFragment: SupportMapFragment, afterGet: (map: GoogleMap) -> Unit) {
            mapFragment.getMapAsync(afterGet)
        }

        fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double, zoom: Float)
        {
            val cameraposition = CameraPosition.builder().target(LatLng(latitude, longitude)).zoom(zoom).build()
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraposition))
        }

        fun addPin(map: GoogleMap, latitude: Double, longitude: Double, title: String): Marker
        {
            return map.addMarker(MarkerOptions().position(LatLng(latitude, longitude)).title(title))
        }

        /*
            If not has permission, call to request permision
         */
        @SuppressLint("MissingPermission")
        fun showUserPosition(context: Context, map: GoogleMap, activity: Activity)
        {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                try
                {
                    map.isMyLocationEnabled = true

                } catch (ex: SecurityException)
                {
                    Log.d("Shops", ex.localizedMessage)
                }
            } else
            {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),10)
            }
        }
    }
}
