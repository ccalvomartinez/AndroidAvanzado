package com.calvo.carolina.madridshops.router

import android.support.v7.app.AppCompatActivity
import com.calvo.carolina.business.model.Shop
import com.calvo.carolina.madridshops.activities.DetailActivity
import com.calvo.carolina.madridshops.activities.ListActivity

class Router
{
    companion object
    {
        fun navigateToShopList(activity: AppCompatActivity)
        {
            activity.startActivity(ListActivity.intentShops(activity))
        }

        fun navigateToDetail(activity: AppCompatActivity, shop: Shop)
        {
            activity.startActivity(DetailActivity.intent(activity, shop))
        }
    }
}