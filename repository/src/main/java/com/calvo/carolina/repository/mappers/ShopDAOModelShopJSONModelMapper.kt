package com.calvo.carolina.repository.mappers

import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.network.json.model.ShopJsonModel

internal fun ShopJsonModel.map(): ShopEntity
{
    val gps_lat = this.gps_lat.toFloatOrNull() ?: Float.NaN
    val gps_lon = this.gps_lon.toFloatOrNull() ?: Float.NaN
    return ShopEntity(
            this.id,
            -1,
            this.name,
            this.description_es,
            gps_lat,
            gps_lon,
            this.img,
            this.logo_img,
            this.opening_hours_es,
            this.address
    )
}