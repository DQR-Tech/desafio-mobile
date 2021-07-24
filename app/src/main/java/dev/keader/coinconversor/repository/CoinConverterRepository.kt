package dev.keader.coinconversor.repository

import dev.keader.coinconversor.database.dao.CurrencyDAO
import dev.keader.coinconversor.database.dao.ExchangeDAO
import dev.keader.coinconversor.database.model.DatabaseUtil
import dev.keader.coinconversor.network.CurrencyLayerService
import dev.keader.coinconversor.network.model.CurrencyDTO
import dev.keader.coinconversor.network.model.ExchangeDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CoinConverterRepository @Inject constructor(
    private val currencyDAO: CurrencyDAO,
    private val exchangeDAO: ExchangeDAO,
    private val currencyLayerService: CurrencyLayerService
) {

    suspend fun getUpdatedDataFromNetwork(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val exchangeDTO = currencyLayerService.getExchanges()
                val currencyDTO = currencyLayerService.getCurrencies()
                if (!validateDTOs(exchangeDTO, currencyDTO))
                    return@withContext false

                val exchanges = DatabaseUtil.convertExchangeDTO(exchangeDTO)
                val currencies = DatabaseUtil.convertCurrencyDTO(currencyDTO)

                exchangeDAO.clearAndInsert(exchanges)
                currencyDAO.clearAndInsert(currencies)
            } catch (ex: Exception) {
                Timber.e(ex)
                return@withContext false
            }
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
}
