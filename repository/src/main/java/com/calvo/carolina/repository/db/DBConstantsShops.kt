package com.calvo.carolina.repository.db

internal object DBConstantsShops
{
     const val TABLE_SHOP = "SHOP"

    // Table field constants
    const val KEY_SHOP_DATABASE_ID = "_id"
    const val KEY_SHOP_ID_JSON = "ID_JSON"
    const val KEY_SHOP_NAME = "NAME"
    const val KEY_SHOP_IMAGE_URL = "IMAGE_URL"
    const val KEY_SHOP_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"

    const val KEY_SHOP_ADDRESS = "ADDRESS"
    const val KEY_SHOP_URL = "URL"
    const val KEY_SHOP_DESCRIPTION_ES = "DESCRIPTION_ES"
    const val KEY_SHOP_DESCRIPTION_EN = "DESCRIPTION_EN"

    const val KEY_SHOP_LATITUDE = "LATITUDE"
    const val KEY_SHOP_LONGITUDE = "LONGITUDE"
    const val KEY_SHOP_OPENING_HOURS_ES = "OPENING_HOURS_ES"
    const val KEY_SHOP_OPENING_HOURS_EN = "OPENING_HOURS_EN"

    val ALL_COLUMNS = arrayOf(
            KEY_SHOP_DATABASE_ID,
            KEY_SHOP_ID_JSON,
            KEY_SHOP_NAME,
            KEY_SHOP_IMAGE_URL,
            KEY_SHOP_LOGO_IMAGE_URL,
            KEY_SHOP_ADDRESS,
            KEY_SHOP_URL,
            KEY_SHOP_DESCRIPTION_ES,
            KEY_SHOP_DESCRIPTION_EN,
            KEY_SHOP_LATITUDE,
            KEY_SHOP_LONGITUDE,
            KEY_SHOP_OPENING_HOURS_ES,
            KEY_SHOP_OPENING_HOURS_EN)

    private const val SQL_SCRIPT_CREATE_SHOP_TABLE = (
            "create table " + TABLE_SHOP
                    + "( "
                    + KEY_SHOP_DATABASE_ID + " integer primary key autoincrement, "
                    + KEY_SHOP_ID_JSON + " integer, "
                    + KEY_SHOP_NAME + " text not null,"
                    + KEY_SHOP_IMAGE_URL + " text, "
                    + KEY_SHOP_LOGO_IMAGE_URL + " text, "
                    + KEY_SHOP_ADDRESS + " text,"
                    + KEY_SHOP_URL + " text,"
                    + KEY_SHOP_LATITUDE + " real,"
                    + KEY_SHOP_LONGITUDE + " real, "
                    + KEY_SHOP_DESCRIPTION_ES + " text, "
                    + KEY_SHOP_DESCRIPTION_EN + " text, "
                    + KEY_SHOP_OPENING_HOURS_ES + " text, "
                    + KEY_SHOP_OPENING_HOURS_EN + " text "
                    + ");")

    const val DROP_DATABASE_SCRIPTS = ""

    val CREATE_DATABASE_SCRIPTS = arrayOf(SQL_SCRIPT_CREATE_SHOP_TABLE)

}
