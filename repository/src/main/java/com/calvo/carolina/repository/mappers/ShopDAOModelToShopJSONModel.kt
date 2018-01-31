package com.calvo.carolina.repository.mappers

import com.calvo.carolina.repository.db.models.ShopDAOModel
import com.calvo.carolina.repository.network.json.model.ShopJsonModel

fun mapShopDAOModelToShopJSONModel(shopJsonModel: ShopJsonModel): ShopDAOModel
{
     return ShopDAOModel(
             shopJsonModel.id,
             -1,
             shopJsonModel.name,
             shopJsonModel.description_es,
             shopJsonModel.gps_lat,
             shopJsonModel.gps_lon,
             shopJsonModel.img,
             shopJsonModel.logo_img,
             shopJsonModel.opening_hours_es,
             shopJsonModel.address
     )
}