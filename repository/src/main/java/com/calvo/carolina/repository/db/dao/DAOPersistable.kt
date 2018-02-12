package com.calvo.carolina.repository.db.dao

import android.database.Cursor

interface DAOWritable<T> {
    fun insert(element: T): Long

    fun insert(elements: List<T>): Boolean
    /**
     * Actualiza el elemento con databaseID con los datos de elemento
     * Devuelve el número de registros afectados
     */
    fun update(databaseID: Long, element: T): Long
    /**
     * deletes the element passed from DB
     */
    fun delete(element: T): Long

    /**
     * delete the element with id from DB
     */
    fun delete(databaseID: Long): Long
    fun deleteAll(): Boolean
    }

interface  DAOReadable<T> {
    fun query(databaseID: Long): T?
    fun query(): List<T>
    fun queryCursor(databaseID: Long): Cursor
}
interface DAOPersistable<T>: DAOReadable<T>, DAOWritable<T>


