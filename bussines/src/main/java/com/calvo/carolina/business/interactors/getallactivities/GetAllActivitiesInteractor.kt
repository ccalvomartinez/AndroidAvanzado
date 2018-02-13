package com.calvo.carolina.business.interactors.getallactivities

import com.calvo.carolina.business.model.Places
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.SuccessClosure

interface GetAllActivitiesInteractor
{
    fun execute(isSpanish: Boolean, success: SuccessClosure<Places>, error: ErrorClosure)
}