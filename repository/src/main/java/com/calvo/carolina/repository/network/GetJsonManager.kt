package com.calvo.carolina.repository.network

import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.SuccessClosure


internal interface GetJsonManager
{
    fun execute(url:String, success: SuccessClosure<String>, error: ErrorClosure)
}