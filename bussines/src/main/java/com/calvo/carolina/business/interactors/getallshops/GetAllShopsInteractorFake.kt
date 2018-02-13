package com.calvo.carolina.business.interactors.getallshops

import com.calvo.carolina.business.model.Place
import com.calvo.carolina.business.model.Places
import com.calvo.carolina.business.model.Shop
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.SuccessClosure


class GetAllShopsInteractorFake: GetAllShopsInteractor
{


    // Metodo "Kotlinero" de hacer las cosas
    override fun execute(isSpanish: Boolean, success: SuccessClosure<Places>, error: ErrorClosure) {
        val allOk = true

        if (allOk) {
            success(createListOfShops())
        } else {
            error("Ha habido un error al descargar")
        }
    }
    private fun createListOfShops(): Places {
        val list = ArrayList<Place>()
        for (i in 0..100) {
            val shop = Shop(i, "Shop" + i, "Address " + i,"","", 2.0, 2.0, "", "")
            list.add(shop)
        }
        return Places(list)
    }
}