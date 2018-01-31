package com.calvo.carolina.repository.network

import com.calvo.carolina.repository.ErrorClosure
import com.calvo.carolina.repository.SuccessClosure


interface GetJsonManager
{
    fun execute(url:String, success: SuccessClosure, error: ErrorClosure)
}