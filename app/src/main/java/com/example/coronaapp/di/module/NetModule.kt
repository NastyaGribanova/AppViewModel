package com.example.coronaapp.di.module

import com.example.coronaapp.BuildConfig
import com.example.coronaapp.corona.CoronaService
import com.example.coronaapp.di.scope.ApplicationScope
import com.example.coronaapp.di.scope.NetScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory,
        @Named(TAG_BASE_URL) url: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideService(retrofit: Retrofit): CoronaService {
        return retrofit.create(CoronaService::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(
        @Named(TAG_AUTH) authInterceptor: Interceptor,
        @Named(TAG_LOGGING) loggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideRxJavaCallAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Provides
    @ApplicationScope
    @Named(TAG_AUTH)
    fun provideAuthInterceptor(
    ): Interceptor = Interceptor { chain ->
        val newUrl = chain
            .request()
            .url()
            .newBuilder()
            .build()

        val newRequest = chain.request().newBuilder().url(newUrl).build()
        chain.proceed(newRequest)
    }

    @Provides
    @ApplicationScope
    @Named(TAG_LOGGING)
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @ApplicationScope
    fun provideConvertFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @ApplicationScope
    @Named(TAG_BASE_URL)
    fun provideBaseURL(): String = BuildConfig.API_ENDPOINT

    companion object {
        private const val TAG_LOGGING = "tag_logging"
        private const val TAG_AUTH = "tag_auth"
        private const val TAG_BASE_URL = "tag_base_url"
    }

}