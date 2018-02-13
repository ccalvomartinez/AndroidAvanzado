package com.calvo.carolina.business.model

interface ReadAggregate<out T> {
    fun count(): Int
    fun all(): List<T>
    fun get(position: Int): T
}

interface WriteAggregate<in T> {
    fun add(element: T)
    fun delete(position: Int)
    fun delete(element: T)
}
interface Aggregate<T>: ReadAggregate<T>, WriteAggregate<T>