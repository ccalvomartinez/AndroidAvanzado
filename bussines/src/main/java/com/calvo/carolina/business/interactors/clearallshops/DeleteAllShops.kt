package com.calvo.carolina.business.interactors.clearallshops

import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure


interface DeleteAllShops
{
    fun execute(success: CodeClosure, error: ErrorClosure)
}