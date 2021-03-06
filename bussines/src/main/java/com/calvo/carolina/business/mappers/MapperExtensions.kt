package com.calvo.carolina.business.mappers

import com.calvo.carolina.business.model.Activity
import com.calvo.carolina.business.model.Shop
import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.models.ShopEntity


internal fun ShopEntity.map(isSpanish: Boolean): Shop
{
    return if (isSpanish)
    {
        Shop(this.id.toInt(), this.name, this.description_es,this.openingHours_es,this.address, this.latitude.toDouble(), this.longitude.toDouble(), this.image, this.logo)
    } else
    {
        Shop(this.id.toInt(), this.name, this.description_en,this.openingHours_en,this.address, this.latitude.toDouble(), this.longitude.toDouble(), this.image, this.logo)
    }
}

internal fun ActivityEntity.map(isSpanish: Boolean): Activity
{
    return if (isSpanish)
    {
        Activity(this.id.toInt(), this.name, this.description_es,this.openingHours_es,this.address, this.latitude.toDouble(), this.longitude.toDouble(), this.image, this.logo)
    } else
    {
        Activity(this.id.toInt(), this.name, this.description_en,this.openingHours_en,this.address, this.latitude.toDouble(), this.longitude.toDouble(), this.image, this.logo)
    }
}