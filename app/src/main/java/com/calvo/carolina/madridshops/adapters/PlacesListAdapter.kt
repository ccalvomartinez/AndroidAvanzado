package com.calvo.carolina.madridshops.adapters


import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.calvo.carolina.business.model.Place
import com.calvo.carolina.business.model.Places
import com.calvo.carolina.madridshops.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_places_list.view.*


class PlacesListAdapter(private val places: Places): RecyclerView.Adapter<PlacesListAdapter.PlaceViewHolder>()
{
    var listener: OnRowClickListener? = null

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
        holder?.bindPlace(places.get(position))
    }

    override fun getItemCount(): Int
    {
        return places.count()
    }

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindPlace(place: Place)
        {

            itemView.cell_list_name.text = place.name
            itemView.cell_list_opening.text = place.openingHours
            Picasso.with(itemView.context)
                    .load(place.logo)
                    .placeholder(R.drawable.tienda_ico)
                    .into(itemView.cell_list_image)
            itemView.setOnClickListener { listener?.onPlaceSelected(place) }
        }
    }

    interface OnRowClickListener
    {
        fun onPlaceSelected(place: Place)
    }
}