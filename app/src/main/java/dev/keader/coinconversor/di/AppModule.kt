package dev.keader.coinconversor.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.keader.coinconversor.BuildConfig
import dev.keader.coinconversor.CoinConverter
import dev.keader.coinconversor.database.CoinConverterDatabase
import dev.keader.coinconversor.model.Constants.ACCESS_KEY
import dev.keader.coinconversor.model.Constants.BASE_URL
import dev.keader.coinconversor.model.Constants.DATABASE_NAME
import dev.keader.coinconversor.network.CurrencyLayerService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: CoinConverter): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CoinConverterDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CoinConverterDatabase::class.java,
            DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrencyDAO(database: CoinConverterDatabase) = database.currencyDAO

    @Provides
    @Singleton
    fun provideExchangeDAO(database: CoinConverterDatabase) = database.exchangeDAO

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create()
    }

    @Provides
    @Singleton
    fun provideCurrencyLayerService(client: OkHttpClient, gson: Gson) : CurrencyLayerService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(CurrencyLayerService::class.java)
    }

    @Provides
    @Singleton
    fun interceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val headers = request.headers.newBuilder()
            .add("Accept", "application/json")
            .build()

        val url = request.url.newBuilder()
            .addQueryParameter("access_key", ACCESS_KEY)
            .build()

        val newRequest = request.newBuilder().url(url).headers(headers).build()
        chain.proceed(newRequest)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .followRedirects(true)
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor {
                    Timber.d(it)
                }.apply {
                    level = if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BASIC
                    else
                        HttpLoggingInterceptor.Level.NONE
                }
            )
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Reusable
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}
