package com.calvo.carolina.bussines.interactors.getallshops

import com.calvo.carolina.bussines.interactors.ErrorClosure
import com.calvo.carolina.bussines.interactors.ErrorCompletion
import com.calvo.carolina.bussines.interactors.SuccessClosure
import com.calvo.carolina.bussines.interactors.SuccessCompletion
import com.calvo.carolina.bussines.model.Shops

interface GetAllShopsInteractor
{
    fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion)
    fun execute(success: SuccessClosure, error: ErrorClosure)
}