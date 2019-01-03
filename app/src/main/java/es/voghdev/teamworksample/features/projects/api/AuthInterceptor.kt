package es.voghdev.teamworksample.features.projects.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(val apiToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain?.request()?.newBuilder()?.addHeader("Authorization", apiToken)
            ?.addHeader("Accept", "application/json")?.build()
            ?: Request.Builder().build()

        return chain?.proceed(request) ?: Response.Builder().build()
    }
}
