package com.calvo.carolina.repository

import com.calvo.carolina.repository.cache.Cache
import com.calvo.carolina.repository.mappers.map
import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.network.GetJsonManager
import com.calvo.carolina.repository.network.json.JsonEntitiesParser
import com.calvo.carolina.repository.network.json.model.ListActivitiesJsonEntity
import com.calvo.carolina.repository.network.json.model.ListShopsJsonEntity
import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure

class RepositoryImpl internal constructor(private val cache: Cache, private val jsonManager: GetJsonManager): Repository
{
    override fun getAllActivities(success: (shops: List<ActivityEntity>) -> Unit, error: ErrorClosure)
    {
        // Read all shops from cache
        cache.getAllActivities(
                success = {
                    success(it)
                },
                error = {
                    populateCacheActivities(success, error)
                })
    }


    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: ErrorClosure)
    {
        // Read all shops from cache
        cache.getAllShops(
                success = {
                    success(it)
                },
                error = {
                    populateCacheShops(success, error)
                })

    }

    private fun populateCacheShops(success: (shops: List<ShopEntity>) -> Unit, error: ErrorClosure)
    {
        jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL,
                { shopsJson: String ->
                    try
                    {
                        val parser = JsonEntitiesParser()
                        val shops = parser.parse<ListShopsJsonEntity>(shopsJson)
                        cache.saveAllShops(shops.result.map { it.map() },
                                success = {
                                    success(shops.result.map { it.map() })
                                },
                                error = {
                                    error(it)
                                })
                    }
                    catch (e: Exception)
                    {
                        error(e.localizedMessage)
                    }
                },
                { errorMessage: String ->
                    error(errorMessage)
                })
    }

    private fun populateCacheActivities(success: (activities: List<ActivityEntity>) -> Unit, error: ErrorClosure)
    {
        jsonManager.execute(BuildConfig.MADRID_ACTIVITIES_SERVER_URL,
                { activitiesJson: String ->
            try
                    {
                        val parser = JsonEntitiesParser()
                        val activities = parser.parse<ListActivitiesJsonEntity>(activitiesJson)
                        cache.saveAllActivities(activities.result.map { it.map() },
                                success = {
                                    success(activities.result.map { it.map() })
                                },
                                error = {
                                    error(it)
                                })
                    }
                    catch (e: Exception)
                    {
                        error(e.localizedMessage)
                    }
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