package com.calvo.carolina.repository.db

internal object DBConstantsActivities
{
     const val TABLE_ACTIVITY = "ACTIVITY"

    // Table field constants
    const val KEY_ACTIVITY_DATABASE_ID = "_id"
    const val KEY_ACTIVITY_ID_JSON = "ID_JSON"
    const val KEY_ACTIVITY_NAME = "NAME"
    const val KEY_ACTIVITY_IMAGE_URL = "IMAGE_URL"
    const val KEY_ACTIVITY_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"

    const val KEY_ACTIVITY_ADDRESS = "ADDRESS"
    const val KEY_ACTIVITY_URL = "URL"
    const val KEY_ACTIVITY_DESCRIPTION_ES = "DESCRIPTION_ES"
    const val KEY_ACTIVITY_DESCRIPTION_EN = "DESCRIPTION_EN"

    const val KEY_ACTIVITY_LATITUDE = "LATITUDE"
    const val KEY_ACTIVITY_LONGITUDE = "LONGITUDE"
    const val KEY_ACTIVITY_OPENING_HOURS_ES = "OPENING_HOURS_ES"
    const val KEY_ACTIVITY_OPENING_HOURS_EN = "OPENING_HOURS_EN"

    val ALL_COLUMNS = arrayOf(
            KEY_ACTIVITY_DATABASE_ID,
            KEY_ACTIVITY_ID_JSON,
            KEY_ACTIVITY_NAME,
            KEY_ACTIVITY_IMAGE_URL,
            KEY_ACTIVITY_LOGO_IMAGE_URL,
            KEY_ACTIVITY_ADDRESS,
            KEY_ACTIVITY_URL,
            KEY_ACTIVITY_DESCRIPTION_ES,
            KEY_ACTIVITY_DESCRIPTION_EN,
            KEY_ACTIVITY_LATITUDE,
            KEY_ACTIVITY_LONGITUDE,
            KEY_ACTIVITY_OPENING_HOURS_ES,
            KEY_ACTIVITY_OPENING_HOURS_EN)

    private const val SQL_SCRIPT_CREATE_ACTIVITY_TABLE = (
            "create table " + TABLE_ACTIVITY
                    + "( "
                    + KEY_ACTIVITY_DATABASE_ID + " integer primary key autoincrement, "
                    + KEY_ACTIVITY_ID_JSON + " integer, "
                    + KEY_ACTIVITY_NAME + " text not null,"
                    + KEY_ACTIVITY_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_LOGO_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_ADDRESS + " text,"
                    + KEY_ACTIVITY_URL + " text,"
                    + KEY_ACTIVITY_LATITUDE + " real,"
                    + KEY_ACTIVITY_LONGITUDE + " real, "
                    + KEY_ACTIVITY_DESCRIPTION_ES + " text, "
                    + KEY_ACTIVITY_DESCRIPTION_EN + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS_ES + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS_EN + " text "
                    + ");")

    const val DROP_DATABASE_SCRIPTS = ""

    val CREATE_DATABASE_SCRIPTS = arrayOf(SQL_SCRIPT_CREATE_ACTIVITY_TABLE)

}
