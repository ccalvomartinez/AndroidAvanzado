package com.calvo.carolina.madridshops.router

import android.support.v7.app.AppCompatActivity
import com.calvo.carolina.business.model.Place
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

        fun navigateToActivitiesList(activity: AppCompatActivity)
        {
            activity.startActivity(ListActivity.intentActivities(activity))
        }

        fun navigateToDetail(activity: AppCompatActivity, place: Place)
        {
            activity.startActivity(DetailActivity.intent(activity, place))
        }
    }
}