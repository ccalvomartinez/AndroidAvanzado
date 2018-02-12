package com.calvo.carolina.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper





class DBHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version)
{
    // Se ejecuta cada vez que se abre la base de datos
    override fun onOpen(connection: SQLiteDatabase?)
    {
        // connection en realidad es una conexión a la base de datos
        super.onOpen(connection)

        // Para poder ejecutar borrados en cascada
        connection?.setForeignKeyConstraintsEnabled(true)
    }

    // Se ejecuta cada vez que se crea la base de datos
    override fun onCreate(db: SQLiteDatabase?)
    {
        // db en realidad es una conexión a la base de datos
        DBConstants.CREATE_DATABASE_SCRIPTS.forEach { db?.execSQL(it)}
    }

    // Se ejecuta cada vez que aumentamos la versión de la base de datos.
    // Esto nos permite ejecutar los scripts necesarios para pasar los datos de la versión antigua a la nueva
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        // db en realidad es una conexión a la base de datos

    }
}

// Helpers
fun convert(boolean: Boolean): Int {
    return if (boolean) 1 else 0
}

fun convert(int: Int): Boolean {
    return  !(int == 0)
}