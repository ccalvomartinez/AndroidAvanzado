package com.calvo.carolina.repository.cache

import com.calvo.carolina.repository.db.dao.DAOPersistable
import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.dispatchOnMainThread

internal class CacheDBImpl(private val shopDAO: DAOPersistable<ShopEntity>, private val activityDAO: DAOPersistable<ActivityEntity>) : Cache
{
    override fun getAllActivities(success: (activities: List<ActivityEntity>) -> Unit, error: ErrorClosure)
    {
        Thread(Runnable
        {

            try
            {
                val activityList = activityDAO.query()

                if (activityList.isNotEmpty())
                {
                    dispatchOnMainThread(Runnable { success(activityList) })
                }
                else
                {
                    dispatchOnMainThread(Runnable { error("No shops") })
                }
            }
            catch (e: Exception)
            {
                dispatchOnMainThread(Runnable { error(e.localizedMessage) })
            }

        }).run()
    }

    override fun saveAllActivities(activities: List<ActivityEntity>, success: CodeClosure, error: ErrorClosure)
    {
        Thread(Runnable
        {
            try
            {
                activityDAO.insert(activities)
                dispatchOnMainThread(Runnable(success))

            } catch (e: Exception)
            {
                dispatchOnMainThread(Runnable { error(e.localizedMessage) })
            }
        }).run()
    }

    override fun deleteAllActivities(success: CodeClosure, error: ErrorClosure)
    {
        Thread(Runnable
        {
            val successDeleting = activityDAO.deleteAll()

            if (successDeleting)
            {
                dispatchOnMainThread(Runnable(success))
            }
            else
            {
                dispatchOnMainThread(Runnable { error("Error deleting") })
            }

        }).run()
    }

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: ErrorClosure)
    {
        Thread(Runnable
        {

            val shopList = shopDAO.query()

            if (shopList.isNotEmpty())
            {
                dispatchOnMainThread(Runnable { success(shopList) })
            }
            else
            {
                dispatchOnMainThread(Runnable { error("No shops") })
            }

        }).run()
    }

    override fun saveAllShops(shops: List<ShopEntity>, success: CodeClosure, error: ErrorClosure)
    {
        Thread(Runnable
        {
            try
            {
                shopDAO.insert(shops)
                dispatchOnMainThread(Runnable(success))

            } catch (e: Exception)
            {
                dispatchOnMainThread(Runnable { error(e.localizedMessage) })
            }
        }).run()
    }

    override fun deleteAllShops(success: CodeClosure, error: ErrorClosure)
    {
        Thread(Runnable
        {
            val successDeleting = shopDAO.deleteAll()

            if (successDeleting)
            {
                dispatchOnMainThread(Runnable(success))
            }
            else
            {
                dispatchOnMainThread(Runnable { error("Error deleting") })
            }

        }).run()
    }
}