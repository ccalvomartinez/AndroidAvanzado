package com.calvo.carolina.madridshops

class MapsConstants
{
    companion object
    {
        val centerPosition = Position(40.416775, -3.703790)
        const val listMapZoom = 12f
        const val detailMapZoom = 15f
    }
}

data class Position(val latitude: Double, val longitude: Double)