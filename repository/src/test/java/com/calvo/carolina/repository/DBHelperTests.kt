package com.calvo.carolina.repository

import org.junit.Test

import com.calvo.carolina.repository.db.convert
import org.junit.Assert.*

class DBHelperTests
{
    @Test
    @Throws(Exception::class)
    fun convert_given_false_return_0()
    {
        assertEquals(0, convert(false).toLong())
    }

    @Test
    @Throws(Exception::class)
    fun convert_given_true_return_1()
    {
        assertEquals(1, convert(true))
    }
}