package com.calvo.carolina.business.model

class Places(private val places: MutableList<Place>): Aggregate<Place>
{
    override fun count(): Int
    {
        return places.count()
    }

    override fun all(): List<Place>
    {
        return places
    }

    override fun get(position: Int): Place
    {
        return places[position]
    }

    override fun add(element: Place)
    {
        places.add(element)
    }

    override fun delete(position: Int)
    {
        if (position < places.size)
        {
            places.removeAt(position)
        }
    }

    override fun delete(element: Place)
    {
        places.remove(element)
    }
}