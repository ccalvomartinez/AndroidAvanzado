package com.calvo.carolina.madridshops.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.calvo.carolina.business.model.Place
import com.calvo.carolina.madridshops.MapsConstants
import com.calvo.carolina.madridshops.R
import com.calvo.carolina.util.GoogleMapsUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity()
{
    companion object
    {
        const val DETAIL_PLACE = "DETAIL_PLACE"
        fun intent(context: Context, place: Place): Intent
        {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DETAIL_PLACE, place)
            return intent
        }
    }

    private val place by lazy {
        intent.getSerializableExtra(DETAIL_PLACE) as Place
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupMap()
        renderView()
    }


    private lateinit var googleMap: GoogleMap

    private fun setupMap()
    {
        GoogleMapsUtil.initializeMap(supportFragmentManager.findFragmentById(R.id.detail_map_fragment) as SupportMapFragment,
                { map ->
                    googleMap = map
                    GoogleMapsUtil.centerMapInPosition(map, place.latitude, place.longitude, MapsConstants.detailMapZoom)
                    setMapSettings(map)
                    GoogleMapsUtil.showUserPosition(baseContext, map, this)
                    addShopPin()
                })
    }

    private fun setMapSettings(map: GoogleMap)
    {
        map.uiSettings.isRotateGesturesEnabled = false
        map.uiSettings.isZoomControlsEnabled = true

    }

    private fun addShopPin()
    {
        GoogleMapsUtil.addPin(googleMap, place.latitude, place.longitude, place.name)
    }
    private fun renderView()
    {
        updateActionBarTitle(place.name)
        detail_description.text = place.description
        detail_address.text = place.address
        detail_opening.text = place.openingHours

    }
    private fun updateActionBarTitle(title:String)
    {
        supportActionBar?.title = title
    }
}
