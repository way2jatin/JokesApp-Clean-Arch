package com.unlimit.jokes.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.unlimit.data.source.remote.base.JokesApi
import com.unlimit.presentation.util.NetworkState
import com.unlimit.jokes.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


const val BASE_URL = "https://geek-jokes.sameerkumar.website"
const val TIME_OUT = 30L
const val HEADER_CACHE_CONTROL = "Cache-Control"
const val HEADER_PRAGMA = "Pragma"


val networkModule = module {

    single { createJokeService(get()) }

    single { createRetrofit(get(), get()) }

    single { createOkHttpClient(get()) }

    single { Moshi.Builder().build() }

}


fun createOkHttpClient(context: Context): OkHttpClient {
    val cacheSize: Long = 10 * 1024 * 1024 // 10 MB
    val cache = Cache(context.cacheDir, cacheSize)

    val client = OkHttpClient.Builder()
        .cache(cache) //To use cache
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addNetworkInterceptor(interceptor)
    }

    client.addInterceptor { chain ->
        var request: Request = chain.request()
        val maxStale = 60 * 60 * 24 * 7
        if (!NetworkState.isAvailable(context)) {
            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .header(HEADER_CACHE_CONTROL, "public, max-stale=$maxStale")
                .build()
        }
        chain.proceed(request)
    }.build()

    client.addNetworkInterceptor { chain ->
        val request = chain.request().newBuilder().build()
        chain.proceed(request)
    }.build()

    return client.build()
}

fun createRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
}

fun createJokeService(retrofit: Retrofit): JokesApi {
    return retrofit.create(JokesApi::class.java)
}
