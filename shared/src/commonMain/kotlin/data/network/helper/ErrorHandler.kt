package com.myapplication.shared.data.network.helper


import core.myDispatchers
import data.network.helper.ExceptionClass
import data.network.helper.NetworkError
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.withContext

suspend inline fun <reified T> handleErrors(
    crossinline response: suspend () -> HttpResponse
): T = withContext(myDispatchers.io) {

    val result = try {
        response()
    } catch(e: IOException) {
        throw ExceptionClass(NetworkError.ServiceUnavailable)
    }

    when(result.status.value) {
        in 200..299 -> Unit
        in 400..499 -> throw ExceptionClass(NetworkError.ClientError)
        500 -> throw ExceptionClass(NetworkError.ServerError)
        else -> throw ExceptionClass(NetworkError.UnknownError)
    }

    return@withContext try {
        result.body()
    } catch(e: Exception) {
        throw ExceptionClass(NetworkError.ServerError)
    }

}