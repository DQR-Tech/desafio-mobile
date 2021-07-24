package dev.keader.coinconversor.database.model

import dev.keader.coinconversor.network.model.CurrencyDTO
import dev.keader.coinconversor.network.model.ExchangeDTO

object DatabaseUtil {

    fun convertCurrencyDTO(currencyDTO: CurrencyDTO): List<Currency> {
        val currencies = mutableListOf<Currency>()
        currencyDTO.currencies.forEach { (code, name) ->
            currencies.add(Currency(0, code, name))
        }
        return currencies
    }

    fun convertExchangeDTO(exchangeDTO: ExchangeDTO): List<Exchange> {
        val exchanges = mutableListOf<Exchange>()
        exchangeDTO.quotes.forEach { (code, value) ->
            var coinCode = code.replace(exchangeDTO.source, "")
            // Removing "USD" of "USDUSD" result into a empty string
            if (coinCode.isEmpty())
                coinCode = exchangeDTO.source
            exchanges.add(Exchange(0, coinCode, value))
        }
        return exchanges
    }
}
