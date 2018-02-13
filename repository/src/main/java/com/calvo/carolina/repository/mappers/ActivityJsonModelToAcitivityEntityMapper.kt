package com.calvo.carolina.repository.mappers

import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.network.json.model.ActivityJsonModel

internal fun ActivityJsonModel.map(): ActivityEntity
{
    val gpsLat = this.gps_lat.toFloatOrNull() ?: Float.NaN
    val gpsLon = this.gps_lon.toFloatOrNull() ?: Float.NaN
    return ActivityEntity(
            this.id,
            -1,
            this.name,
            this.description_es,
            this.description_en,
            gpsLat,
            gpsLon,
            this.img,
            this.logo_img,
            this.opening_hours_es,
            this.opening_hours_en,
            this.address
    )
}