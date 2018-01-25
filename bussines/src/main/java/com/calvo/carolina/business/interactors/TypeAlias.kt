package com.calvo.carolina.business.interactors

import com.calvo.carolina.business.model.Shops

typealias SuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (errorMessage: String) -> Unit
typealias CodeClosure = () -> Unit