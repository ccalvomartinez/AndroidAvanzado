package com.calvo.carolina.bussines.interactors.getallshops

import com.calvo.carolina.bussines.interactors.ErrorClosure
import com.calvo.carolina.bussines.interactors.ErrorCompletion
import com.calvo.carolina.bussines.interactors.SuccessClosure
import com.calvo.carolina.bussines.interactors.SuccessCompletion
import com.calvo.carolina.bussines.model.Shop
import com.calvo.carolina.bussines.model.Shops


class GetAllShopsInteractorFake: GetAllShopsInteractor
{
    // Metodo "Javero" de hacer las cosas
    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion)
    {
        var allOK = true
        // Fake connect to de repository
        // .....
        // Set allOK to true or false

        if (allOK){
            success.successCompletion(createListOfShops())
        } else {
            error.errorCompletion("Ha habido un error al descargar la lista de tiendas")
        }

    }

    // Metodo "Kotlinero" de hacer las cosas
    override fun execute(success: SuccessClosure, error: ErrorClosure) {
        val allOk = true

        if (allOk) {
            success(createListOfShops())
        } else {
            error("Ha habido un error al descargar")
        }
    }
    private fun createListOfShops(): Shops {
        val list = ArrayList<Shop>()
        for (i in 0..100) {
            val shop = Shop(i, "Shop" + i, "Address " + i)
            list.add(shop)
        }
        return Shops(list)
    }
}