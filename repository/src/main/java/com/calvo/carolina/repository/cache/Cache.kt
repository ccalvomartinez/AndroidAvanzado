package com.calvo.carolina.repository.cache

import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure

internal interface Cache
{
    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: ErrorClosure)
    fun saveAllShops(shops: List<ShopEntity>, success: CodeClosure, error: ErrorClosure)
    fun deleteAllShops(success: CodeClosure, error: ErrorClosure)
    fun getAllActivities(success: (activities: List<ActivityEntity>) -> Unit, error: ErrorClosure)
    fun saveAllActivities(activities: List<ActivityEntity>, success: CodeClosure, error: ErrorClosure)
    fun deleteAllActivities(success: CodeClosure, error: ErrorClosure)
}