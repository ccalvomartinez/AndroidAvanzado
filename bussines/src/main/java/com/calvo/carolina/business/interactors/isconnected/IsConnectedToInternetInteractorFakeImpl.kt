package com.calvo.carolina.business.interactors.isconnected

import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure


internal class IsConnectedToInternetInteractorFakeImpl : IsConnectedToInternetInteractor
{
    override fun execute(success: CodeClosure, errorClosure: ErrorClosure)
    {
        success()
    }
}