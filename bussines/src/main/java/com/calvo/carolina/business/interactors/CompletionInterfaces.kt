package com.calvo.carolina.business.interactors


interface  SuccessCompletion<T> {
    fun successCompletion(e: T)
}

interface  ErrorCompletion {
    fun errorCompletion(errorMessage: String)
}