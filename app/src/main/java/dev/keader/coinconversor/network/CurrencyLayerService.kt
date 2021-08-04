package dev.keader.coinconversor.network

import dev.keader.coinconversor.network.model.CurrencyDTO
import dev.keader.coinconversor.network.model.ExchangeDTO
import retrofit2.http.GET

interface CurrencyLayerService {
    @GET("/live")
    suspend fun getExchanges() : ExchangeDTO

    @GET("/list")
    suspend fun getCurrencies() : CurrencyDTO
}
