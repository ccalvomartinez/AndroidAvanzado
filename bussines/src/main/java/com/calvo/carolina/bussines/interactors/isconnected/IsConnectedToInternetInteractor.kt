package com.calvo.carolina.bussines.interactors.isconnected

import com.calvo.carolina.bussines.interactors.CodeClosure
import com.calvo.carolina.bussines.interactors.ErrorClosure

interface IsConnectedToInternetInteractor {
    fun execute(success: CodeClosure, errorClosure: ErrorClosure)
}