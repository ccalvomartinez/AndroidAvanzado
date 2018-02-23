package com.calvo.carolina.repository

import android.content.Context
import com.calvo.carolina.repository.cache.Cache
import com.calvo.carolina.repository.cache.CacheDBImpl
import com.calvo.carolina.repository.db.DBHelper
import com.calvo.carolina.repository.db.dao.ActivityDAO
import com.calvo.carolina.repository.db.dao.DAOPersistable
import com.calvo.carolina.repository.db.dao.ShopDAO
import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.network.GetJsonManager
import com.calvo.carolina.repository.network.GetJsonManagerVolley
import java.lang.ref.WeakReference

open class RepositoryObjectInjector(private val weakContext:  WeakReference<Context>)
{
   open fun buildRepository(): Repository
    {
        return RepositoryImpl(buildCache(), buildGetJsonManager())
    }
    internal fun buildCache(): Cache
    {
        return CacheDBImpl(buildDAOPersistableShops(), buildDAOPersistableActivities())
    }

    internal fun buildDAOPersistableShops(): DAOPersistable<ShopEntity>
    {
        return ShopDAO(weakContext, "MadridShops.sqlite", 1)
    }

    internal fun buildDAOPersistableActivities(): DAOPersistable<ActivityEntity>
    {
        return ActivityDAO(weakContext, "MadridShops.sqlite", 1)
    }

    internal fun buildDBHelper(name: String, version: Int): DBHelper {
        return DBHelper(weakContext.get()!!, name, null, version)
    }

    internal fun buildGetJsonManager(): GetJsonManager
    {
        return GetJsonManagerVolley(weakContext)
    }
}