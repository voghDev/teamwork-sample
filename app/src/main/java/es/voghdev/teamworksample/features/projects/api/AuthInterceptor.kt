package es.voghdev.teamworksample.features.projects.api

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(val apiToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val authorizationHeader = Base64.encodeToString((apiToken).toByteArray(), Base64.NO_WRAP)
        val request = chain?.request()?.newBuilder()?.addHeader("Authorization", "Basic $authorizationHeader")
            ?.addHeader("Accept", "application/json")
            ?.addHeader("Content-Type", "application/json")?.build()
            ?: Request.Builder().build()

        return chain?.proceed(request) ?: Response.Builder().build()
    }
}
