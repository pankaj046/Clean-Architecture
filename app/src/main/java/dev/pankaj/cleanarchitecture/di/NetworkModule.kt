package dev.pankaj.cleanarchitecture.di


import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.pankaj.cleanarchitecture.App
import dev.pankaj.cleanarchitecture.BuildConfig
import dev.pankaj.cleanarchitecture.data.local.prefmanager.SharedPreferencesUtil
import dev.pankaj.cleanarchitecture.data.remote.api.ApiService
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val READ_TIMEOUT = 30
    private val WRITE_TIMEOUT = 30
    private val CONNECTION_TIMEOUT = 10
    private val CACHE_SIZE_BYTES = 10 * 1024 * 1024L


    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor(headerInterceptor)

        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(sharedPreferencesUtil: SharedPreferencesUtil): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
            sharedPreferencesUtil.getString("token")?.let { authToken ->
                request.addHeader("Authorization", "Bearer $authToken")
            }
            chain.proceed(request.build())
        }
    }

    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
    }


    @Provides
    @Singleton
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}
