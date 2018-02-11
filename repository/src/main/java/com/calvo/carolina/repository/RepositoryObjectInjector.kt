package com.calvo.carolina.repository

import android.content.Context
import com.calvo.carolina.repository.Repository
import com.calvo.carolina.repository.RepositoryImpl
import com.calvo.carolina.repository.cache.Cache
import com.calvo.carolina.repository.cache.CacheDBImpl
import com.calvo.carolina.repository.db.DBHelper
import com.calvo.carolina.repository.db.dao.DAOPersistable
import com.calvo.carolina.repository.db.dao.ShopDAO
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.network.GetJsonManager
import com.calvo.carolina.repository.network.GetJsonManagerVolley
import java.lang.ref.WeakReference

class RepositoryObjectInjector(private val weakContext:  WeakReference<Context>)
{
   fun BuildRepository(): Repository
    {
        return RepositoryImpl(weakContext, BuildCache(), BuildGetJsonManager())
    }
    internal fun BuildCache(): Cache
    {
        return CacheDBImpl(weakContext, BuildDAOPersistable())
    }

    internal fun BuildDAOPersistable(): DAOPersistable<ShopEntity>
    {
        return ShopDAO(BuildDBHelper("MadridShops.sqlite", 1))
    }

    internal fun BuildDBHelper(name: String, version: Int): DBHelper {
        return DBHelper(weakContext.get()!!, name, null, version)
    }

    internal fun BuildGetJsonManager(): GetJsonManager
    {
        return GetJsonManagerVolley(weakContext)
    }
}