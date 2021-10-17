package features.common

import features.configurations.SSLConfig
import features.context.TestContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

object CommonApis {
    private val okHttpClient: OkHttpClient = SSLConfig.trustAllSslClient(OkHttpClient())

    fun get(url: String, jwtToken: String) {
        val request = Request.Builder()
                .addHeader("Authorization", "Bearer $jwtToken")
                .addHeader("Context-Type", "application/json")
                .url(url)
                .get()
                .build()
        TestContext.response = okHttpClient.newCall(request).execute()
    }

    fun post(url: String, jwtToken: String, requestBody: RequestBody) {
        val request = Request.Builder()
                .addHeader("Authorization", "Bearer $jwtToken")
                .addHeader("Context-Type", "application/json")
                .url(url)
                .post(requestBody)
                .build()
        TestContext.response = okHttpClient.newCall(request).execute()
    }

    fun put(url: String, jwtToken: String, requestBody: RequestBody) {
        val request = Request.Builder()
                .addHeader("Authorization", "Bearer $jwtToken")
                .addHeader("Context-Type", "application/json")
                .url(url)
                .put(requestBody)
                .build()
        TestContext.response = okHttpClient.newCall(request).execute()
    }

    fun delete(url: String, jwtToken: String) {
        val request = Request.Builder()
                .addHeader("Authorization", "Bearer $jwtToken")
                .addHeader("Context-Type", "application/json")
                .url(url)
                .delete()
                .build()
        TestContext.response = okHttpClient.newCall(request).execute()
    }

    fun login(url: String, requestBody: RequestBody): Response {
        val request = Request.Builder()
                .post(requestBody)
                .url(url)
                .build()
        return okHttpClient.newCall(request).execute()
    }
}