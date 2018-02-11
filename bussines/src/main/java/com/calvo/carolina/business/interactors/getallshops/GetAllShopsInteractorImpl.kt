package com.calvo.carolina.business.interactors.getallshops

import android.content.Context
import com.calvo.carolina.business.mappers.map

import com.calvo.carolina.business.model.Shops
import com.calvo.carolina.repository.Repository
import com.calvo.carolina.repository.models.ShopEntity
import com.calvo.carolina.repository.util.RepositoryObjectInjector
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.SuccessClosure
import java.lang.ref.WeakReference

internal class GetAllShopsInteractorImpl(private val weakContext: WeakReference<Context>): GetAllShopsInteractor
{
    private val repository: Repository =  RepositoryObjectInjector(weakContext).BuildRepository()
    // TODO("Añadir idioma")
    override fun execute(success: SuccessClosure<Shops>, error: ErrorClosure)
    {
       repository.getAllShops(
               success = {shopEntityList: List<ShopEntity> ->
                   val shops = Shops(shopEntityList.map { it.map() }.toMutableList())
                   success(shops)
               },
               error = {
                   error(it)
               })
    }
}

