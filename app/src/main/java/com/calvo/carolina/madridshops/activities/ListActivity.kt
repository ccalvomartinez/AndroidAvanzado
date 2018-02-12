package com.calvo.carolina.madridshops.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.calvo.carolina.business.BusinessObjectInjector
import com.calvo.carolina.business.model.Shop
import com.calvo.carolina.business.model.Shops
import com.calvo.carolina.madridshops.R
import com.calvo.carolina.madridshops.adapters.PlaceInfoWindowAdapter
import com.calvo.carolina.madridshops.fragments.ListFragment
import com.calvo.carolina.madridshops.fragments.OnPlaceSelectedListener
import com.calvo.carolina.util.GoogleMapsUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import java.util.*

class ListActivity : AppCompatActivity(), OnPlaceSelectedListener
{



    companion object
    {
        fun intent(context: Context): Intent
        {
            val intent = Intent(context, ListActivity::class.java)
            return intent
        }
    }

    val listFragment by lazy { supportFragmentManager.findFragmentById(R.id.list_fragment) as ListFragment }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        updateActionBarTitle()
        setup()
        Log.d("MadridShops", "onCreate ListActivity")
    }
    // TODO("Poner tiendas o actividades dependiento de quien me llame")
    private fun updateActionBarTitle()
    {
       supportActionBar?.title = getString(R.string.shops)
    }

    private fun setup()
    {
        val getAllShopsInteractor = BusinessObjectInjector(this).BuildGetAllShopsInteractor()

        getAllShopsInteractor.execute(
                Locale.getDefault().language == "es",
                success =
                { shops: Shops ->
                    setupList(shops)
                    setupMap(shops)
                },
                error = {errorMessage: String ->
                    Toast.makeText(baseContext, "Error downloading" + errorMessage, Toast.LENGTH_LONG).show()
                })
    }

    private fun setupList(shops: Shops)
    {
        listFragment.setShops(shops)
    }

    override fun onPlaceSelected(shop: Shop)
    {
        navigateToDetail(shop)
    }

    private fun setupMap(shops: Shops)
    {
        GoogleMapsUtil.initializeMap(supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment,
                { map ->
                    Log.d("Shops", "Hablemu mapa")
                    googleMap = map
                    GoogleMapsUtil.centerMapInPosition(map, 40.416775, -3.703790)
                    setMapSettings(map)
                    GoogleMapsUtil.showUserPosition(baseContext, map, this)
                    addAllPins(map, shops)
                })
    }


    private lateinit var googleMap: GoogleMap

    private fun setMapSettings(map: GoogleMap)
    {
        map.uiSettings.isRotateGesturesEnabled = false
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnInfoWindowClickListener{
            if (it != null){
                navigateToDetail(it.tag as Shop)
            }
        }
        map.setInfoWindowAdapter(PlaceInfoWindowAdapter(baseContext))
    }

    private fun navigateToDetail(shop: Shop)
    {

        startActivity(DetailActivity.intent(this, shop))

    }




    // Cuando pido permisos llego aquí
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10)
        {
            if(grantResults.any { it == PackageManager.PERMISSION_GRANTED })
            {
                GoogleMapsUtil.showUserPosition(baseContext, googleMap, this)
            }
        }
    }



    fun addAllPins(map: GoogleMap, shops: Shops)
    {
        (0 until shops.count())
                .map { shops.get(it) }
                .forEach {
                    val marker = GoogleMapsUtil.addPin(map, it.latitude, it.longitude, it.name)
                    marker.tag = it
                }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId)
        {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy()
    {
        super.onDestroy()

    }
}
