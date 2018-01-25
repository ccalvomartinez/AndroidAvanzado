package com.calvo.carolina.bussines.interactors


interface  SuccessCompletion<T> {
    fun successCompletion(e: T)
}

interface  ErrorCompletion {
    fun errorCompletion(errorMessage: String)
}