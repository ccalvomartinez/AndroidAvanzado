package com.calvo.carolina.repository.db.dao

import android.database.Cursor

interface DAOReadable<T> {
    fun insert()
    fun update()
    fun delete()
    fun deleteAll()
}

interface  DAOWritable<T> {
    fun query(id: Int): T
    fun query(): List<T>
    fun queryCursor(): Cursor
}
interface DAOPersistable<T>: DAOReadable<T>, DAOWritable<T>

