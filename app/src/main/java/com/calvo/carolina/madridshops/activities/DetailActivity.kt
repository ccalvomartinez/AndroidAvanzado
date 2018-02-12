package com.calvo.carolina.madridshops.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.calvo.carolina.madridshops.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        Picasso.with(this)
                .load("http://78.media.tumblr.com/tumblr_ktna2fMosM1qa02x4o1_400.jpg")
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(img1)
        Picasso.with(this)
                .load("https://misanimales.com/wp-content/uploads/2016/10/crecen-los-gatos.jpg")
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(img2)
        Picasso.with(this)
                .load("https://misanimales.com/wp-content/uploads/2016/10/cara-de-un-perro.jpg")
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(img3)
    }
}
