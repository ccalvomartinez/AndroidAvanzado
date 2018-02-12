package com.calvo.carolina.business.interactors.clearallshops

import android.content.Context
import com.calvo.carolina.repository.Repository
import com.calvo.carolina.repository.RepositoryObjectInjector
import com.calvo.carolina.util.CodeClosure
import com.calvo.carolina.util.ErrorClosure
import java.lang.ref.WeakReference

internal class DeleteAllShopsImpl(private val weakContext: WeakReference<Context>) : DeleteAllShops
{
    private  val objectInjector = RepositoryObjectInjector(weakContext)
    override fun execute(success: CodeClosure, error: ErrorClosure)
    {
        val repository: Repository = objectInjector.BuildRepository()
        repository.deleteAllShops(success, error)
    }
}