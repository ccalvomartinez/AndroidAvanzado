package com.calvo.carolina.business.interactors.getallshops

import android.content.Context
import com.calvo.carolina.business.mappers.map
import com.calvo.carolina.business.model.Places
import com.calvo.carolina.repository.Repository
import com.calvo.carolina.repository.RepositoryObjectInjector
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.SuccessClosure
import java.lang.ref.WeakReference

internal class GetAllShopsInteractorImpl(weakContext: WeakReference<Context>): GetAllShopsInteractor
{
    private val repository: Repository =  RepositoryObjectInjector(weakContext).buildRepository()

    override fun execute(isSpanish: Boolean, success: SuccessClosure<Places>, error: ErrorClosure)
    {
       repository.getAllShops(
               success = {shopEntityList: List<ShopEntity> ->
                   val shops = Places(shopEntityList.map { it.map(isSpanish) }.toMutableList())
                   success(shops)
               },
               error = {
                   error(it)
               })
    }
}

