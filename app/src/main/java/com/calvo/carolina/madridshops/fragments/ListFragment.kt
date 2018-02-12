package com.calvo.carolina.madridshops.fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.calvo.carolina.business.model.Shop
import com.calvo.carolina.business.model.Shops

import com.calvo.carolina.madridshops.R
import com.calvo.carolina.madridshops.adapters.PlacesListAdapter


/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment()
{
    private lateinit var root: View
    private var onPlaceSelectedListener: OnPlaceSelectedListener? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        root = inflater!!.inflate(R.layout.fragment_list, container, false)
        return root
    }
    fun setShops(shops: Shops)
    {
        val placesList = root.findViewById<RecyclerView>(R.id.list)
        placesList.layoutManager = LinearLayoutManager(activity)
        placesList.itemAnimator = DefaultItemAnimator()
        val placesListAdapter = PlacesListAdapter(shops)
        placesListAdapter.listener = object : PlacesListAdapter.OnRowClickListener
        {
            override fun onPlaceSelected(shop: Shop)
            {
                onPlaceSelectedListener?.onPlaceSelected(shop)
            }
        }
        placesList.adapter = placesListAdapter

    }
    override fun onAttach(context: Context?)
    {
        super.onAttach(context)
        commonAttach(context)
    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?)
    {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach()
    {
        super.onDetach()
        onPlaceSelectedListener = null
    }
    private fun commonAttach(listener: Any?)
    {
        if (listener is OnPlaceSelectedListener)
        {
            onPlaceSelectedListener = listener
        }
    }
}// Required empty public constructor

interface OnPlaceSelectedListener
{
    fun onPlaceSelected(shop: Shop)
}