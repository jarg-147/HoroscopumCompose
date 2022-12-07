package com.bitbiird.horoscopumcompose.di

import android.content.Context
import com.bitbiird.horoscopumcompose.data.network.HoroscopeApiClient
import com.bitbiird.horoscopumcompose.util.helpers.InternetConnectionHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://aztro.sameerkumar.website"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggin = HttpLoggingInterceptor()
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(loggin)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideHoroscopeApiClient(retrofit: Retrofit): HoroscopeApiClient = retrofit.create(HoroscopeApiClient::class.java)

    @Provides
    @Singleton
    fun provideInternetConnectionHelper(@ApplicationContext context : Context): InternetConnectionHelper = InternetConnectionHelper(context)

}