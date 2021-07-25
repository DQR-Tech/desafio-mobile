package dev.keader.coinconversor.database.model

import dev.keader.coinconversor.network.model.CurrencyDTO
import dev.keader.coinconversor.network.model.ExchangeDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseUtil {

    suspend fun convertCurrencyDTO(currencyDTO: CurrencyDTO): List<Currency> {
        return withContext(Dispatchers.IO) {
            val currencies = mutableListOf<Currency>()
            currencyDTO.currencies.forEach { (code, name) ->
                currencies.add(Currency(0, code, name))
            }
            return@withContext currencies
        }
    }

    suspend fun convertExchangeDTO(exchangeDTO: ExchangeDTO): List<Exchange> {
        return withContext(Dispatchers.IO) {
            val exchanges = mutableListOf<Exchange>()
            exchangeDTO.quotes.forEach { (code, value) ->
                var coinCode = code.replace(exchangeDTO.source, "")
                // Removing "USD" of "USDUSD" result into a empty string
                if (coinCode.isEmpty())
                    coinCode = exchangeDTO.source
                exchanges.add(Exchange(0, coinCode, value))
            }
            return@withContext exchanges
        }
    }
}
