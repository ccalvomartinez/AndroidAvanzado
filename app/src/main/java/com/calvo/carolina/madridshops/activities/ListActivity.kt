package com.calvo.carolina.madridshops.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.calvo.carolina.business.interactors.BusinessObjectInjector
import com.calvo.carolina.business.model.Shops
import com.calvo.carolina.madridshops.R
import com.calvo.carolina.madridshops.fragments.ListFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class ListActivity : AppCompatActivity()
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
        setupList()
        Log.d("MadridShops", "onCreate ListActivity")
    }
    // TODO("Poner tiendas o actividades dependiento de quien me llame")
    private fun updateActionBarTitle()
    {
       supportActionBar?.title = getString(R.string.shops)
    }

    private fun setupList()
    {
        val getAllShopsInteractor = BusinessObjectInjector(this).BuildGetAllShopsInteractor()

        getAllShopsInteractor.execute(
                Locale.getDefault().language == "es",
                success =
                { shops: Shops ->
                    listFragment.setShops(shops)
                },
                error = {errorMessage: String ->
                    Toast.makeText(baseContext, "Error downloading" + errorMessage, Toast.LENGTH_LONG).show()
                })
    }

    private fun setupMap()
    {
        Log.d("Shops", "setupMap")
        val getAllShopsInteractor = BusinessObjectInjector(this).BuildGetAllShopsInteractor()
        getAllShopsInteractor.execute(
                Locale.getDefault().language == "es",
                success =
                { shops: Shops ->
                    Log.d("Shops", "Success")
                    initializeMap(supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment,
                            {map ->
                                Log.d("Shops", "Hablemu mapa")
                                centerMapInPosition(map, 40.416775,-3.703790)
                                map.uiSettings.isRotateGesturesEnabled = false
                                showUserPosition(baseContext, map)
                                addAllPins(map, shops)
                            })
                },
                error = {errorMessage: String ->
                    Log.d("Shops", errorMessage)
                    Toast.makeText(baseContext, "Error downloading" + errorMessage, Toast.LENGTH_LONG).show()
                })
    }

    // TODO("Mover a una clase de utilidad. Toma como parámetros el fragmento de mapa y una clausura que se ejecuta cuando obtenemos el mapa")
    private fun initializeMap(mapFragment: SupportMapFragment, afterGet: (map: GoogleMap) -> Unit) {
        mapFragment.getMapAsync(afterGet)
    }

    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double)
    {
        val cameraposition = CameraPosition.builder().target(LatLng(latitude, longitude)).zoom(12f).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraposition))
    }

    // TODO("Refactorizar esto")
    fun showUserPosition(context: Context, map: GoogleMap)
    {
        if (ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED )
        {
            Log.d("Shops", "No tengo permisos")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),10)
            return
        }
        map.isMyLocationEnabled = true

    }
    // Cuando pido permisos llego aquí
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10)
        {

        }
    }

    fun addPin(map: GoogleMap, latitude: Double, longitude: Double, title: String)
    {
        map.addMarker(MarkerOptions().position(LatLng(latitude, longitude)).title(title))
    }

    fun addAllPins(map: GoogleMap, shops: Shops)
    {
        (0 until shops.count())
                .map { shops.get(it) }
                .forEach { addPin(map, it.latitude, it.longitude, it.name) }
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
}
