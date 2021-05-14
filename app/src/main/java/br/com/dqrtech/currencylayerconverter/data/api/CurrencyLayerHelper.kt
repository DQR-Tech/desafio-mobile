package br.com.dqrtech.currencylayerconverter.data.api

class CurrencyLayerHelper(private val currencyLayerService: CurrencyLayerService) {

    suspend fun getCurrencyList() = currencyLayerService.getCurrencyList()

    suspend fun getConversion(currencies: String) =currencyLayerService.getConversion(currencies)
}