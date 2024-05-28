package com.example.meteoritelandings.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AppTokenInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-App-Token", token)
            .build()
        return chain.proceed(request)
    }
}