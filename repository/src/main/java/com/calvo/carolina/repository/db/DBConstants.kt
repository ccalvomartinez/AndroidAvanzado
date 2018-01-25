package com.calvo.carolina.repository.db

object DBConstants
{
    val TABLE_SHOP = "SHOP"

    // Table field constants
    const val KEY_SHOP_ID = "_id"
    const val KEY_SHOP_NAME = "NAME"
    const val KEY_SHOP_IMAGE_URL = "IMAGE_URL"
    const val KEY_SHOP_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"

    const val KEY_SHOP_ADDRESS = "ADDRESS"
    const val KEY_SHOP_URL = "URL"
    const val KEY_SHOP_DESCRIPTION = "DESCRIPTION"

    const val KEY_SHOP_LATITUDE = "LATITUDE"
    const val KEY_SHOP_LONGITUDE = "LONGITUDE"

    val ALL_COLUMNS = arrayOf(KEY_SHOP_ID, KEY_SHOP_NAME, KEY_SHOP_IMAGE_URL, KEY_SHOP_LOGO_IMAGE_URL, KEY_SHOP_ADDRESS, KEY_SHOP_URL, KEY_SHOP_DESCRIPTION, KEY_SHOP_LATITUDE, KEY_SHOP_LONGITUDE)

    val SQL_SCRIPT_CREATE_SHOP_TABLE = (
            "create table " + TABLE_SHOP
                    + "( "
                    + KEY_SHOP_ID + " integer primary key autoincrement, "
                    + KEY_SHOP_NAME + " text not null,"
                    + KEY_SHOP_IMAGE_URL + " text, "
                    + KEY_SHOP_LOGO_IMAGE_URL + " text, "
                    + KEY_SHOP_ADDRESS + " text,"
                    + KEY_SHOP_URL + " text,"
                    + KEY_SHOP_LATITUDE + " real,"
                    + KEY_SHOP_LONGITUDE + " real, "
                    + KEY_SHOP_DESCRIPTION + " text "
                    + ");")

    val DROP_DATABASE_SCRIPTS = ""

    val CREATE_DATABASE_SCRIPTS = arrayOf(SQL_SCRIPT_CREATE_SHOP_TABLE)
}
