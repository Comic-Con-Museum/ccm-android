package com.comic_con.museum.ar.persistence

import com.comic_con.museum.ar.persistence.responses.WebResponsePayload
import okhttp3.Request


/**
 * This class serves as an intermediary between the OkHttp3 Request class and the client
 */
class WebRequest(
    private val endpoint: WebRequestEndPoint,
    val responseClass: Class<out WebResponsePayload>
) {

    // TODO update to actual URL
    @Suppress("PrivatePropertyName")
    private val BASE_URL = "https://reqres.in/api/"

    fun buildOkHttpRequest(): Request {
        // If more builder functions are added, they must also be implemented here
        return Request.Builder()
            .url("$BASE_URL${this.endpoint}")
            .build()
    }

    class Builder (
        private val endPoint: WebRequestEndPoint,
        private val responseClass: Class<out WebResponsePayload>
    ) {

        // Create function
        fun create(): WebRequest? {
            return WebRequest(endPoint, responseClass)
        }

        // Builder functions
        // TODO add functionality here for constructing a web request
    }

}