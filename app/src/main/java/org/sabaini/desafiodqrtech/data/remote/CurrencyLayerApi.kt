package org.sabaini.desafiodqrtech.data.remote

import org.sabaini.desafiodqrtech.utilities.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyLayerApi {

    @GET("list")
    suspend fun getCurrencies(@Query("access_key") access_key: String = API_KEY): Currencies

    @GET("live")
    suspend fun getRates(@Query("access_key") access_key: String = API_KEY): Quotes
}