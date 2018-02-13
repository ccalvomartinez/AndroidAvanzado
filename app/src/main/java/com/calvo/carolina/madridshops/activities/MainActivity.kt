package com.calvo.carolina.madridshops.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.calvo.carolina.madridshops.R
import com.calvo.carolina.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setShopsButton()

    }
    private fun setShopsButton()
    {
        val shopButton  = findViewById<View>(R.id.go_shops)
        shopButton.setOnClickListener { _ ->
            Router.navigateToShopList(this)

        }
    }
}
