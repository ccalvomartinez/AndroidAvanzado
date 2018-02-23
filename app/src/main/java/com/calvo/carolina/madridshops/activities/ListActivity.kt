package com.calvo.carolina.madridshops.activities


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.calvo.carolina.business.BusinessObjectInjector
import com.calvo.carolina.business.model.Place
import com.calvo.carolina.business.model.Places
import com.calvo.carolina.madridshops.MapsConstants
import com.calvo.carolina.madridshops.R
import com.calvo.carolina.madridshops.adapters.PlaceInfoWindowAdapter
import com.calvo.carolina.madridshops.fragments.ListFragment
import com.calvo.carolina.madridshops.fragments.OnPlaceSelectedListener
import com.calvo.carolina.madridshops.router.Router
import com.calvo.carolina.util.GoogleMapsUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AppCompatActivity(), OnPlaceSelectedListener
{


    companion object
    {
        private const val GO_TO = "GO_TO"
        private const val SHOPS = "SHOPS"
        private const val ACTIVITIES = "ACTIVITIES"
        fun intentShops(context: Context): Intent
        {
            val intent = Intent(context, ListActivity::class.java)
            intent.putExtra(GO_TO, SHOPS)
            return intent
        }

        fun intentActivities(context: Context): Intent
        {
            val intent = Intent(context, ListActivity::class.java)
            intent.putExtra(GO_TO, ACTIVITIES)
            return intent
        }
    }

    private val listFragment by lazy { supportFragmentManager.findFragmentById(R.id.list_fragment) as ListFragment }
    private lateinit var placeType: String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        placeType = intent.getStringExtra(GO_TO)?: ACTIVITIES
        updateActionBarTitle()
        setup()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if (requestCode == DetailActivity.REQ_CODE && resultCode == Activity.RESULT_OK)
        {
            data?.let {
                placeType = it.getStringExtra(GO_TO)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

     private fun updateActionBarTitle()
    {
        supportActionBar?.title = if (placeType == SHOPS) getString(R.string.shops) else getString(R.string.activities)
    }

    private fun setup()
    {
        togleActivity(true)

        if (placeType == SHOPS)
        {
            val getAllShopsInteractor = BusinessObjectInjector(this).buildGetAllShopsInteractor()
            getAllShopsInteractor.execute(
                    Locale.getDefault().language == "es",
                    success =
                    { places: Places ->
                        onGetPlaces(places)
                    },
                    error = { _: String ->
                        onErrorGettingPlaces()
                    })
        }
        else
        {
            val getAllActivitiesInteractor = BusinessObjectInjector(this).buildGetAllActivitiesInteractor()
            getAllActivitiesInteractor.execute(
                    Locale.getDefault().language == "es",
                    success =
                    { places: Places ->
                        onGetPlaces(places)
                    },
                    error = { _: String ->
                        onErrorGettingPlaces()
                    })
        }

    }

    private fun togleActivity(isVisible: Boolean)
    {
        list_activity_indicator.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun onGetPlaces(places: Places)
    {
        setupList(places)
        setupMap(places)
        togleActivity(false)
    }

    private fun onErrorGettingPlaces()
    {

        Snackbar.make(findViewById<LinearLayout>(R.id.list_root_view), getString(R.string.error_downloading), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.list_activity_retry), { view -> setup() })
                .show()
        togleActivity(false)
    }

    private fun setupList(places: Places)
    {
        listFragment.setPlaces(places)
    }

    override fun onPlaceSelected(place: Place)
    {
        Router.navigateToDetail(this, place)
    }

    private lateinit var googleMap: GoogleMap

    private fun setupMap(places: Places)
    {
        GoogleMapsUtil.initializeMap(supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment,
                { map ->
                    googleMap = map
                    GoogleMapsUtil.centerMapInPosition(map, MapsConstants.centerPosition.latitude, MapsConstants.centerPosition.longitude, MapsConstants.listMapZoom)
                    setMapSettings(map)
                    GoogleMapsUtil.showUserPosition(baseContext, map, this)
                    addAllPins(map, places)
                })
    }

    private fun setMapSettings(map: GoogleMap)
    {
        map.uiSettings.isRotateGesturesEnabled = false
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnInfoWindowClickListener {
            if (it != null)
            {
                Router.navigateToDetail(this, it.tag as Place)
            }
        }
        map.setInfoWindowAdapter(PlaceInfoWindowAdapter(baseContext))
    }

    // Cuando pido permisos llego aquí
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10)
        {
            if (grantResults.any { it == PackageManager.PERMISSION_GRANTED })
            {
                GoogleMapsUtil.showUserPosition(baseContext, googleMap, this)
            }
        }
    }

    private fun addAllPins(map: GoogleMap, places: Places)
    {
        (0 until places.count())
                .map { places.get(it) }
                .forEach {
                    val marker = GoogleMapsUtil.addPin(map, it.latitude, it.longitude, it.name)
                    marker.tag = it
                }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.menu_main, menu)
        return false
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
