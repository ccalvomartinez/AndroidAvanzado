package com.calvo.carolina.business.mappers

import com.calvo.carolina.business.model.Shop
import com.calvo.carolina.repository.models.ShopEntity

// TODO("Añadir parametro idioma")
internal fun ShopEntity.map(): Shop
{
    return Shop(this.id.toInt(), this.name,this.address, this.latitude.toDouble(), this.longitude.toDouble())
}