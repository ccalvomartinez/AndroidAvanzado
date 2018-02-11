package com.calvo.carolina.repository

import android.content.Context
import android.util.Log
import com.calvo.carolina.repository.cache.*
import com.calvo.carolina.repository.mappers.map
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.network.GetJsonManager
import com.calvo.carolina.repository.network.GetJsonManagerVolley
import com.calvo.carolina.repository.network.json.JsonEntitiesParser
import com.calvo.carolina.repository.network.json.model.ListShopsJsonEntity
import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure
import java.lang.ref.WeakReference

class RepositoryImpl internal constructor(private val weakContext:  WeakReference<Context>, private val cache: Cache, private val jsonManager: GetJsonManager): Repository
{

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: ErrorClosure)
    {
        // Read all shops from cache
        cache.getAllShops(
                success = {
                // if there are shops in cache -> return
                    Log.d("Shops","Repository, shop count: " + it.count().toString())
                    success(it)
                },
                error = {
                    // if no shops -> Network

                    populateCache(success, error)
                })

    }

    private fun populateCache(success: (shops: List<ShopEntity>) -> Unit, error: ErrorClosure)
    {
        Log.d("Shops", "Populate cache")
        // perform network request
       jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL,
                { shopsJson: String ->
                    val parser = JsonEntitiesParser()
                    val shops = parser.parse<ListShopsJsonEntity>(shopsJson)
                    // store result in cache
                    cache.saveAllShops(shops.result.map { it.map() },
                            success = {
                                success(shops.result.map { it.map() })
                            },
                            error = {
                                error(it)
                            })
                },
                { errorMessage: String ->
                    error(errorMessage)
                })
    }

    override fun deleteAllShops(successClosure: CodeClosure, errorClosure: ErrorClosure)
    {
        cache.deleteAllShops(successClosure, errorClosure)
    }
}