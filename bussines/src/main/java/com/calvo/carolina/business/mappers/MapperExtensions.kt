package com.calvo.carolina.business.mappers

import com.calvo.carolina.business.model.Shop
import com.calvo.carolina.repository.models.ShopEntity


internal fun ShopEntity.map(isSpanish: Boolean): Shop
{
    if (isSpanish)
    {
        return Shop(this.id.toInt(), this.name, this.description_es,this.openingHours_es,this.address, this.latitude.toDouble(), this.longitude.toDouble(), this.image, this.logo)
    } else
    {
        return Shop(this.id.toInt(), this.name, this.description_en,this.openingHours_en,this.address, this.latitude.toDouble(), this.longitude.toDouble(), this.image, this.logo)
    }
}