package com.calvo.carolina.repository.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.calvo.carolina.repository.db.DBConstants
import com.calvo.carolina.repository.db.DBHelper
import com.calvo.carolina.repository.models.ShopEntity
import java.lang.ref.WeakReference

// Inyectamos la dependencia de DBHelper
class ShopDAO(weakContext: WeakReference<Context>, name: String, version: Int) : DAOPersistable<ShopEntity>
{
    private var dbHelper = DBHelper(weakContext.get()!!, name, null, version)

    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun insert(element: ShopEntity): Long
    {
        dbReadWriteConnection.beginTransaction()
        val id: Long
        try
        {
            id = dbReadWriteConnection.insert(DBConstants.TABLE_SHOP, null, contentValues(element))
            dbReadWriteConnection.setTransactionSuccessful()

        }
        finally
        {
            dbReadWriteConnection.endTransaction()
        }
        return id
    }

    override fun insert(elements: List<ShopEntity>): Boolean
    {
        dbReadWriteConnection.beginTransaction()
        var isSuccessfulInsert = true
        try
        {
            for (i in 0 until elements.count())
            {

                val id = dbReadWriteConnection.insert(DBConstants.TABLE_SHOP, null, contentValues(elements[i]))
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

    private fun contentValues(shopEntity: ShopEntity): ContentValues
    {
        val content = ContentValues()

        content.put(DBConstants.KEY_SHOP_ID_JSON, shopEntity.id)
        content.put(DBConstants.KEY_SHOP_NAME, shopEntity.name)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_ES, shopEntity.description_es)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_EN, shopEntity.description_en)
        content.put(DBConstants.KEY_SHOP_LATITUDE, shopEntity.latitude)
        content.put(DBConstants.KEY_SHOP_LONGITUDE, shopEntity.longitude)
        content.put(DBConstants.KEY_SHOP_IMAGE_URL, shopEntity.image)
        content.put(DBConstants.KEY_SHOP_LOGO_IMAGE_URL, shopEntity.logo)
        content.put(DBConstants.KEY_SHOP_ADDRESS, shopEntity.address)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_ES, shopEntity.openingHours_es)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_EN, shopEntity.openingHours_en)

        return content
    }

    override fun update(databaseID: Long, element: ShopEntity): Long
    {
        dbReadWriteConnection.beginTransaction()
        val registersAffected: Long
        try
        {
            registersAffected = dbReadWriteConnection.update(DBConstants.TABLE_SHOP, contentValues(element), DBConstants.KEY_SHOP_DATABASE_ID + " = ?", arrayOf(databaseID.toString())).toLong()
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

    override fun delete(element: ShopEntity): Long
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
            registersAffected = dbReadWriteConnection.delete(DBConstants.TABLE_SHOP, DBConstants.KEY_SHOP_DATABASE_ID + " = ?", arrayOf(databaseID.toString())).toLong()

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
            successfulDelete = dbReadWriteConnection.delete(DBConstants.TABLE_SHOP, null, null).toLong() >= 0

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
    override fun query(databaseID: Long): ShopEntity?
    {
        val cursor = queryCursor(databaseID)
        cursor.moveToFirst()

        return entityFromCursor(cursor)

    }

    override fun query(): List<ShopEntity>
    {
        val queryResult = ArrayList<ShopEntity>()

        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID
        )

        while (cursor.moveToNext())
        {
            queryResult.add(entityFromCursor(cursor)!!)
        }

        return queryResult
    }

    private fun entityFromCursor(cursor: Cursor): ShopEntity?
    {
        if (cursor.isBeforeFirst || cursor.isAfterLast)
        {
            return null
        }
        return ShopEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_ID_JSON)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION_EN)),
                cursor.getFloat(cursor.getColumnIndex(DBConstants.KEY_SHOP_LATITUDE)),
                cursor.getFloat(cursor.getColumnIndex(DBConstants.KEY_SHOP_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_ADDRESS))
        )
    }

    override fun queryCursor(databaseID: Long): Cursor
    {
        return dbReadOnlyConnection.query(
                DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_SHOP_DATABASE_ID + " = ?",
                arrayOf(databaseID.toString()),
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID
        )
    }
}
