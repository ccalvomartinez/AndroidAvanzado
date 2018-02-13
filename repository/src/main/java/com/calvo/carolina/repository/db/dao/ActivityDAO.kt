package com.calvo.carolina.repository.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.calvo.carolina.repository.db.DBConstantsActivities
import com.calvo.carolina.repository.db.DBHelper
import com.calvo.carolina.repository.models.ActivityEntity
import java.lang.ref.WeakReference

// Inyectamos la dependencia de DBHelper
class ActivityDAO(weakContext: WeakReference<Context>, name: String, version: Int) : DAOPersistable<ActivityEntity>
{
    private var dbHelper = DBHelper(weakContext.get()!!, name, null, version)

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun insert(element: ActivityEntity): Long
    {
        dbReadWriteConnection.beginTransaction()
        val id: Long
        try
        {
            id = dbReadWriteConnection.insert(DBConstantsActivities.TABLE_ACTIVITY, null, contentValues(element))
            dbReadWriteConnection.setTransactionSuccessful()

        }
        finally
        {
            dbReadWriteConnection.endTransaction()
        }
        return id
    }

    override fun insert(elements: List<ActivityEntity>): Boolean
    {
        dbReadWriteConnection.beginTransaction()
        var isSuccessfulInsert = true
        try
        {
            for (i in 0 until elements.count())
            {

                val id = dbReadWriteConnection.insert(DBConstantsActivities.TABLE_ACTIVITY, null, contentValues(elements[i]))
                if (id <= 0)
                {
                    isSuccessfulInsert = false
                    break
                }
            }
            if (isSuccessfulInsert)
            {
                dbReadWriteConnection.setTransactionSuccessful()
            }
        }
        finally
        {
            dbReadWriteConnection.endTransaction()
        }
        return isSuccessfulInsert
    }

    private fun contentValues(activityEntity: ActivityEntity): ContentValues
    {
        val content = ContentValues()

        content.put(DBConstantsActivities.KEY_ACTIVITY_ID_JSON, activityEntity.id)
        content.put(DBConstantsActivities.KEY_ACTIVITY_NAME, activityEntity.name)
        content.put(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_ES, activityEntity.description_es)
        content.put(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_EN, activityEntity.description_en)
        content.put(DBConstantsActivities.KEY_ACTIVITY_LATITUDE, activityEntity.latitude)
        content.put(DBConstantsActivities.KEY_ACTIVITY_LONGITUDE, activityEntity.longitude)
        content.put(DBConstantsActivities.KEY_ACTIVITY_IMAGE_URL, activityEntity.image)
        content.put(DBConstantsActivities.KEY_ACTIVITY_LOGO_IMAGE_URL, activityEntity.logo)
        content.put(DBConstantsActivities.KEY_ACTIVITY_ADDRESS, activityEntity.address)
        content.put(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_ES, activityEntity.openingHours_es)
        content.put(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_EN, activityEntity.openingHours_en)

        return content
    }

    override fun update(databaseID: Long, element: ActivityEntity): Long
    {
        dbReadWriteConnection.beginTransaction()
        val registersAffected: Long
        try
        {
            registersAffected = dbReadWriteConnection.update(DBConstantsActivities.TABLE_ACTIVITY, contentValues(element), DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID + " = ?", arrayOf(databaseID.toString())).toLong()
            if (registersAffected > 0)
            {
                dbReadWriteConnection.setTransactionSuccessful()
            }

        }
        finally
        {
            dbReadWriteConnection.endTransaction()
        }
        return registersAffected
    }

    override fun delete(element: ActivityEntity): Long
    {
        if (element.databaseID < 1)
        {
            return 0
        }
        return delete(element.databaseID)
    }

    /**
     * Where clause
     * 1. "NAME = PEPE" or "NAME = " + name (esto permite inyección de SQL)
     * 2. WhereClause = "NAME = ? AND LAST = ?" y WhereArguments = arrayOf("pepe", "gonzalez")
     */
    override fun delete(databaseID: Long): Long
    {
        dbReadWriteConnection.beginTransaction()
        val registersAffected: Long
        try
        {
            registersAffected = dbReadWriteConnection.delete(DBConstantsActivities.TABLE_ACTIVITY, DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID + " = ?", arrayOf(databaseID.toString())).toLong()

            if (registersAffected > 0)
            {
                dbReadWriteConnection.setTransactionSuccessful()
            }

        }
        finally
        {
            dbReadWriteConnection.endTransaction()
        }
        return registersAffected
    }

    override fun deleteAll(): Boolean
    {
        dbReadWriteConnection.beginTransaction()
        val successfulDelete: Boolean
        try
        {
            successfulDelete = dbReadWriteConnection.delete(DBConstantsActivities.TABLE_ACTIVITY, null, null).toLong() >= 0

            if (successfulDelete)
            {
                dbReadWriteConnection.setTransactionSuccessful()
            }

        }
        finally
        {
            dbReadWriteConnection.endTransaction()
        }
        return successfulDelete
    }

    /**
     *  El cursor tiene una posición BEFORE FIRST y una posición AFTER LAST
     *  BEFORE FIRST
     *  Name= pepe, Last = rodriguez
     *  AFTER LAST
     *  Cuando nos devuelve el cursor está en la posición BEFORE FIRST
     *  Por eso hay que hacer un moveToFirst() para acceder al registro
     */
    override fun query(databaseID: Long): ActivityEntity?
    {
        val cursor = queryCursor(databaseID)
        cursor.moveToFirst()

        return entityFromCursor(cursor)

    }

    override fun query(): List<ActivityEntity>
    {
        val queryResult = ArrayList<ActivityEntity>()

        val cursor = dbReadOnlyConnection.query(
                DBConstantsActivities.TABLE_ACTIVITY,
                DBConstantsActivities.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID
        )

        while (cursor.moveToNext())
        {
            queryResult.add(entityFromCursor(cursor)!!)
        }

        return queryResult
    }

    private fun entityFromCursor(cursor: Cursor): ActivityEntity?
    {
        if (cursor.isBeforeFirst || cursor.isAfterLast)
        {
            return null
        }
        return ActivityEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_ID_JSON)),
                cursor.getLong(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_DESCRIPTION_EN)),
                cursor.getFloat(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_LATITUDE)),
                cursor.getFloat(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_OPENING_HOURS_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivities.KEY_ACTIVITY_ADDRESS))
        )
    }

    override fun queryCursor(databaseID: Long): Cursor
    {
        return dbReadOnlyConnection.query(
                DBConstantsActivities.TABLE_ACTIVITY,
                DBConstantsActivities.ALL_COLUMNS,
                DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID + " = ?",
                arrayOf(databaseID.toString()),
                "",
                "",
                DBConstantsActivities.KEY_ACTIVITY_DATABASE_ID
        )
    }
}
