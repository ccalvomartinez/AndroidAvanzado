package com.calvo.carolina.madridshops.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.calvo.carolina.madridshops.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setAddDishButton()

    }
    private fun setAddDishButton()
    {
        val addDishButton  = findViewById<View>(R.id.go_shops)
        addDishButton.setOnClickListener { _ ->
            startActivity(ListActivity.intent(this))
        }
    }
}
