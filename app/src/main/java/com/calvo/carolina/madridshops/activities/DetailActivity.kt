package com.calvo.carolina.madridshops.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.calvo.carolina.business.model.Shop
import com.calvo.carolina.madridshops.R
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity()
{
    companion object
    {
        const val DETAIL_SHOP = "DETAIL_SHOP"
        fun intent(context: Context, shop: Shop): Intent
        {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DETAIL_SHOP, shop)
            return intent
        }
    }

    val shop by lazy {
        intent.getSerializableExtra(DETAIL_SHOP) as Shop
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        renderView()
    }

    private fun renderView()
    {
        detail_description.text = shop.description
        detail_address.text = shop.address
        detail_opening.text = shop.openingHours

    }
}
