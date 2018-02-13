package com.calvo.carolina.repository.cache

import android.util.Log
import com.calvo.carolina.repository.db.dao.DAOPersistable
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.dispatchOnMainThread

internal class CacheDBImpl(private val shopDAO: DAOPersistable<ShopEntity>) : Cache
{

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: ErrorClosure)
    {
        Log.d("Shops", "Cache GetAllShops")
        Thread(Runnable
        {

            val shopList = shopDAO.query()

            if (shopList.isNotEmpty())
            {
                Log.d("Shops", "Cache List from database")
                dispatchOnMainThread(Runnable { success(shopList) })
            }
            else
            {
                Log.d("Shops", "Cache List empty")
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
                Log.d("Shops", "Save shops")

                shopDAO.insert(shops)
                dispatchOnMainThread(Runnable(success))

            } catch (e: Exception)
            {
                Log.d("Shops", "Error saving: " + e.localizedMessage)
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