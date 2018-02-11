package com.calvo.carolina.util

typealias SuccessClosure<T> = (element: T) -> Unit
typealias ErrorClosure = (errorMessage: String) -> Unit
typealias CodeClosure = () -> Unit