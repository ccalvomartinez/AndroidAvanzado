package com.calvo.carolina.business.interactors.getallshops


import com.calvo.carolina.business.model.Shops
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.SuccessClosure

interface GetAllShopsInteractor
{
    fun execute(success: SuccessClosure<Shops>, error: ErrorClosure)
}