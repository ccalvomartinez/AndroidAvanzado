package com.calvo.carolina.util

import android.os.Handler
import android.os.Looper

class RunnableUtils
{
    companion object
    {
        fun dispatchOnMainThread(runnable: Runnable)
        {
            val uiHandler = Handler(Looper.getMainLooper())
            uiHandler.post(runnable)
        }
    }
}
