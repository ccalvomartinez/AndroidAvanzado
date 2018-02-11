package com.calvo.carolina.madridshops.adapters


import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.calvo.carolina.business.model.Shop
import com.calvo.carolina.business.model.Shops
import com.calvo.carolina.madridshops.R
import kotlinx.android.synthetic.main.cell_places_list.view.*


class PlacesListAdapter(private val shops: Shops): RecyclerView.Adapter<PlacesListAdapter.PlaceViewHolder>()
{
    var listener: OnPlaceSelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlaceViewHolder
    {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.cell_places_list, parent, false)
        if (Build.VERSION.SDK_INT >= 21)
        {
            view.elevation = 4f
        }
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder?, position: Int)
    {
        holder?.bindShop(shops.get(position))
    }

    override fun getItemCount(): Int
    {
        return shops.count()
    }

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindShop(shop: Shop)
        {
            // TODO("Mostrar imagen y horario de apertura")
            itemView.cell_list_name.text = shop.name
            itemView.cell_list_opening.text = shop.openingHours
            itemView.setOnClickListener { listener?.onPlaceSelected(shop) }
        }
    }

    interface OnPlaceSelectedListener
    {
        fun onPlaceSelected(shop: Shop)
    }
}