package com.calvo.carolina.bussines.interactors

import com.calvo.carolina.bussines.model.Shops

typealias SuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (errorMessage: String) -> Unit
typealias CodeClosure = () -> Unit