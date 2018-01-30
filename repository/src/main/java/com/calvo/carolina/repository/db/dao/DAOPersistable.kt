package com.calvo.carolina.repository.db.dao

import android.database.Cursor

interface DAOWritable<T> {
    fun insert(element: T): Long
    fun update(id: Long, element: T): Long
    /**
     * deletes the element passed from DB
     */
    fun delete(element: T): Long

    /**
     * delete the element with id from DB
     */
    fun delete(id: Long): Long
    fun deleteAll()
}

interface  DAOReadable<T> {
    fun query(id: Long): T
    fun query(): List<T>
    fun queryCursor(id: Long): Cursor
}
interface DAOPersistable<T>: DAOReadable<T>, DAOWritable<T>

