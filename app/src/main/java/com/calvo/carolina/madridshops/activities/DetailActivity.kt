package com.calvo.carolina.madridshops.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.calvo.carolina.business.model.Shop
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
        const val DETAIL_SHOP = "DETAIL_SHOP"
        fun intent(context: Context, shop: Shop): Intent
        {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DETAIL_SHOP, shop)
            return intent
        }
    }

    private val shop by lazy {
        intent.getSerializableExtra(DETAIL_SHOP) as Shop
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
                    Log.d("Shops", "Hablemu mapa")
                    googleMap = map
                    GoogleMapsUtil.centerMapInPosition(map, shop.latitude, shop.longitude, MapsConstants.detailMapZoom)
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
        GoogleMapsUtil.addPin(googleMap, shop.latitude, shop.longitude, shop.name)
    }
    private fun renderView()
    {
        updateActionBarTitle(shop.name)
        detail_description.text = shop.description
        detail_address.text = shop.address
        detail_opening.text = shop.openingHours

    }
    private fun updateActionBarTitle(title:String)
    {
        supportActionBar?.title = title
    }
}
