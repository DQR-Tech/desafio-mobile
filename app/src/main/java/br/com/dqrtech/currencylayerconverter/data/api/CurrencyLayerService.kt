package br.com.dqrtech.currencylayerconverter.data.api

import br.com.dqrtech.currencylayerconverter.BuildConfig
import br.com.dqrtech.currencylayerconverter.data.model.ConversionResponse
import br.com.dqrtech.currencylayerconverter.data.model.CurrenciesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyLayerService {

    @GET("/list")
    suspend fun getCurrencyList(
        @Query("access_key") key: String = BuildConfig.CURRENCY_LAYER_ACCESS_KEY,
        @Query("format") format: String = "1"
    ) : CurrenciesResponse

    @GET("/live")
    suspend fun getConversion(
        @Query("currencies") currencies: String,
        @Query("access_key") key: String = BuildConfig.CURRENCY_LAYER_ACCESS_KEY
    ) : ConversionResponse

    companion object {
        private const val BASE_URL = "http://api.currencylayer.com/"

        fun create(): CurrencyLayerService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CurrencyLayerService::class.java)
        }
    }
}