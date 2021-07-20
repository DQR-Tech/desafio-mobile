package br.com.dqrtech.currencylayerconverter.data.repository

import br.com.dqrtech.currencylayerconverter.data.api.CurrencyLayerHelper

class CurrencyLayerRepository (private val currencyLayerHelper: CurrencyLayerHelper) {

    suspend fun getCurrencyList() = currencyLayerHelper.getCurrencyList()

    suspend fun getConversion(currencies: String) = currencyLayerHelper.getConversion(currencies)
}