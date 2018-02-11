package com.calvo.carolina.repository


import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.calvo.carolina.repository.network.GetJsonManager
import com.calvo.carolina.repository.network.GetJsonManagerVolley
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * ----------------------------------------------------------------------------------
 *   OJO CUIDAO!! Para que funcionen los tests hay que crear una congiguración de RUN
 *   Run -> Edit Configurations -> + -> Android Instrumented Tests
 * ----------------------------------------------------------------------------------
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class GetJsonManagerVolleyTest
{
    val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    @Throws(Exception::class)
    fun given_url_gets_not_null_Json_as_string()
    {
        val url = "http://madrid-shops.com/json_new/getShops.php"

        val jsonManager: GetJsonManager = GetJsonManagerVolley(appContext)

        jsonManager.execute(url,
                { json: String ->
                    assertTrue(json.isNotEmpty())
                },
                { errorMessage ->
                    assertTrue(errorMessage,false)
                })
    }


}
