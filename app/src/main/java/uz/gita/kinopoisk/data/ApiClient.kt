package uz.gita.kinopoisk.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .addInterceptor(httpLogging)
        .build()

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://s3-eu-west-1.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}