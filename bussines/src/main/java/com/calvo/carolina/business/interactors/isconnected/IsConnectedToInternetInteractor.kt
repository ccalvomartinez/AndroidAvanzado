package com.calvo.carolina.business.interactors.isconnected

import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure


internal interface IsConnectedToInternetInteractor {
    fun execute(success: CodeClosure, errorClosure: ErrorClosure)
}