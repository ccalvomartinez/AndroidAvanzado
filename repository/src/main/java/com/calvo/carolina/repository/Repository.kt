package com.calvo.carolina.repository

import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure

interface Repository
{
    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: ErrorClosure)
    fun getAllActivities(success: (shops: List<ActivityEntity>) -> Unit, error: ErrorClosure)
    fun deleteAllShops(successClosure: CodeClosure, errorClosure: ErrorClosure)

}