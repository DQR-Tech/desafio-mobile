package org.sabaini.desafiodqrtech.repositories

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.sabaini.desafiodqrtech.data.local.CurrencyDao
import org.sabaini.desafiodqrtech.data.local.asEntitieCurrency
import org.sabaini.desafiodqrtech.data.local.asEntitieExchangeRate
import org.sabaini.desafiodqrtech.data.remote.CurrencyLayerApi
import org.sabaini.desafiodqrtech.data.remote.asDatabaseCurrency
import org.sabaini.desafiodqrtech.data.remote.asDatabaseExchangeRate
import org.sabaini.desafiodqrtech.entities.Currency
import org.sabaini.desafiodqrtech.entities.ExchangeRate
import java.lang.Exception
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val currencyLayerApi: CurrencyLayerApi
) {

    fun getCurrencies(): Flow<List<Currency>> {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            refreshCurrencies()
        }

        return currencyDao.loadCurrencies().map { list ->
            list.asEntitieCurrency()
        }
    }

    private suspend fun refreshCurrencies() {
        withContext(Dispatchers.IO) {
            try {
                val responseCurrencies = currencyLayerApi.getCurrencies()
                currencyDao.saveCurrencies(responseCurrencies.asDatabaseCurrency())
            } catch (e: Exception) {
                Log.d("CurrencyRepository", e.stackTraceToString())
            }
        }
    }

    fun getCurrenciesFromDatabase(): Flow<List<Currency>> {
        return currencyDao.loadCurrencies().map { list ->
            list.asEntitieCurrency()
        }
    }

    fun getExchangeRates(): Flow<List<ExchangeRate>> {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            refreshExchangeRates()
        }

        return currencyDao.loadExchangeRates().map { list ->
            list.asEntitieExchangeRate()
        }
    }

    private suspend fun refreshExchangeRates() {
        withContext(Dispatchers.IO) {
            try {
                val responseExchangeRates = currencyLayerApi.getRates()
                currencyDao.saveExchangeRate(responseExchangeRates.asDatabaseExchangeRate())
            } catch (e: Exception) {
                Log.d("CurrencyRepository", e.stackTraceToString())
            }
        }
    }
}