package com.calvo.carolina.business.interactors.getallshops

import com.calvo.carolina.business.interactors.ErrorClosure
import com.calvo.carolina.business.interactors.ErrorCompletion
import com.calvo.carolina.business.interactors.SuccessClosure
import com.calvo.carolina.business.interactors.SuccessCompletion
import com.calvo.carolina.business.model.Shops

interface GetAllShopsInteractor
{
    fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion)
    fun execute(success: SuccessClosure, error: ErrorClosure)
}