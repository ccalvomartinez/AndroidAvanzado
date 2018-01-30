package com.calvo.carolina.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.calvo.carolina.repository.db.DBConstants
import com.calvo.carolina.repository.db.DBHelper
import com.calvo.carolina.repository.db.models.ShopDAOModel

// Inyectamos la dependencia de DBHelper
class ShopDAO(val dbHelper: DBHelper): DAOPersistable<ShopDAOModel>
{
    // Conexiones de lectura y escritura a la base de datos
    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private  val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun insert(element: ShopDAOModel): Long
    {
        val id = dbReadWriteConnection.insert(DBConstants.TABLE_SHOP, null, contentValues(element))
        return id
    }

    fun contentValues(shopDAOModel: ShopDAOModel): ContentValues
    {
        val content = ContentValues()

        content.put(DBConstants.KEY_SHOP_ID_JSON, shopDAOModel.id)
        content.put(DBConstants.KEY_SHOP_NAME, shopDAOModel.name)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION, shopDAOModel.description)
        content.put(DBConstants.KEY_SHOP_LATITUDE, shopDAOModel.latitude)
        content.put(DBConstants.KEY_SHOP_LONGITUDE, shopDAOModel.longitude)
        content.put(DBConstants.KEY_SHOP_IMAGE_URL, shopDAOModel.image)
        content.put(DBConstants.KEY_SHOP_LOGO_IMAGE_URL, shopDAOModel.logo)
        content.put(DBConstants.KEY_SHOP_ADDRESS, shopDAOModel.address)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS, shopDAOModel.openingHours)

        return content
    }

    override fun update(databaseID: Long, element: ShopDAOModel): Long
    {
        return dbReadWriteConnection.update(DBConstants.TABLE_SHOP,contentValues(element),DBConstants.KEY_SHOP_DATABASE_ID + " = ?", arrayOf(databaseID.toString())).toLong()
    }

    override fun delete(element: ShopDAOModel): Long
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
         return dbReadWriteConnection.delete(DBConstants.TABLE_SHOP,DBConstants.KEY_SHOP_DATABASE_ID + " = ?", arrayOf(databaseID.toString())).toLong()
    }

    override fun deleteAll(): Boolean
    {
        return dbReadWriteConnection.delete(DBConstants.TABLE_SHOP,null, null).toLong() > 0
    }

    /**
     *  El cursor tiene una posición BEFORE FIRST y una posición AFTER LAST
     *  BEFORE FIRST
     *  Name= pepe, Last = rodriguez
     *  AFTER LAST
     *  Cuando nos devuelve el cursor está en la posición BEFORE FIRST
     *  Por eso hay que hacer un moveToFirst() para acceder al registro
     */
    override fun query(databaseID: Long): ShopDAOModel
    {
        val cursor = queryCursor(databaseID)
        cursor.moveToFirst()

        return entityFromCursor(cursor)!!

    }

    override fun query(): List<ShopDAOModel>
    {
        val queryResult = ArrayList<ShopDAOModel>()

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

    private fun entityFromCursor(cursor: Cursor): ShopDAOModel?
    {
        if (cursor.isBeforeFirst || cursor.isAfterLast)
        {
            return null
        }
        return ShopDAOModel(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_ID_JSON)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION)),
                cursor.getFloat(cursor.getColumnIndex(DBConstants.KEY_SHOP_LATITUDE)),
                cursor.getFloat(cursor.getColumnIndex(DBConstants.KEY_SHOP_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS)),
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
