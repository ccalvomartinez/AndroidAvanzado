package com.calvo.carolina.util

import android.os.Handler
import android.os.Looper

fun Runnable.dispatchOnMainThread()
{
    val uiHandler = Handler(Looper.getMainLooper())
    uiHandler.post(this)
}