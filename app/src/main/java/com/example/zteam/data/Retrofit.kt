package com.example.zteam.data

import com.example.zteam.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object RetrofitInstance {
    private const val BASE_URL = "https://api.rawg.io/api/"
    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val newUrl = originalRequest.url.newBuilder().addQueryParameter("key", BuildConfig.API_KEY).build()
        val modifiedRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(modifiedRequest)
    }
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?
        ) {}

        override fun checkServerTrusted(
            chain: Array<out X509Certificate>?,
            authType: String?
        ) {}

        override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
    })

    val sslContext = SSLContext.getInstance("SSL")
    init{
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
    }
    // Create an ssl socket factory with our all-trusting manager
    val sslSocketFactory = sslContext.socketFactory
    val json = Json {
        ignoreUnknownKeys = true // чтобы не падало на лишних полях
    }

    val api: ZteamApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    .addNetworkInterceptor(authInterceptor)
                    .build()
            )
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ZteamApiService::class.java)
    }
}