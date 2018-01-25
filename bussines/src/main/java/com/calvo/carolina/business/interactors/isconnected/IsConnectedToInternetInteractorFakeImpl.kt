package com.calvo.carolina.business.interactors.isconnected

import com.calvo.carolina.business.interactors.CodeClosure
import com.calvo.carolina.business.interactors.ErrorClosure

class IsConnectedToInternetInteractorFakeImpl : IsConnectedToInternetInteractor
{
    override fun execute(success: CodeClosure, errorClosure: ErrorClosure)
    {
        success()
    }
}