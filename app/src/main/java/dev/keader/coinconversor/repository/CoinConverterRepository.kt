package dev.keader.coinconversor.repository

import dev.keader.coinconversor.database.dao.CurrencyDAO
import dev.keader.coinconversor.database.dao.ExchangeDAO
import dev.keader.coinconversor.database.model.DatabaseUtil
import dev.keader.coinconversor.network.CurrencyLayerService
import dev.keader.coinconversor.network.model.CurrencyDTO
import dev.keader.coinconversor.network.model.ExchangeDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CoinConverterRepository @Inject constructor(
    private val currencyDAO: CurrencyDAO,
    private val exchangeDAO: ExchangeDAO,
    private val currencyLayerService: CurrencyLayerService
) {

    suspend fun updatedDataFromNetwork(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val exchangeDTO = currencyLayerService.getExchanges()
                // Yes folks, free api return success = false if we do it too fast
                delay(2000L)
                val currencyDTO = currencyLayerService.getCurrencies()
                if (!validateDTOs(exchangeDTO, currencyDTO)) {
                    Timber.e("DTO with invalid data")
                    return@withContext false
                }

                val exchanges = DatabaseUtil.convertExchangeDTO(exchangeDTO)
                val currencies = DatabaseUtil.convertCurrencyDTO(currencyDTO)
                exchangeDAO.clearAndInsert(exchanges)
                currencyDAO.clearAndInsert(currencies)
            } catch (ex: Exception) {
                Timber.e(ex)
                return@withContext false
            }
            Timber.d("Updated data with success")
            return@withContext true
        }
    }

    private fun validateDTOs(exchangeDTO: ExchangeDTO, currencyDTO: CurrencyDTO) : Boolean {
        if (!exchangeDTO.success || !currencyDTO.success)
            return false
        if (exchangeDTO.quotes.isEmpty() || currencyDTO.currencies.isEmpty())
            return false
        return true
    }

    fun getAllExchanges() = exchangeDAO.getAllExchanges()

    fun getAllCurrenciesOrderByName() = currencyDAO.getAllCurrenciesOrderByName()

    fun getAllCurrenciesOrderByCode() = currencyDAO.getAllCurrenciesOrderByCode()

    fun getCurrenciesBySearchOrderByName(search: String) =
        currencyDAO.getCurrenciesBySearchOrderByName(search)

    fun getCurrenciesBySearchOrderByCode(search: String) =
        currencyDAO.getCurrenciesBySearchOrderByCode(search)
}
