package dev.keader.coinconversor.database.model

import dev.keader.coinconversor.network.model.CurrencyDTO
import dev.keader.coinconversor.network.model.ExchangeDTO

object DatabaseUtil {

    fun convertCurrencyDTO(currencyDTO: CurrencyDTO) : List<Currency> {
        val currencies = mutableListOf<Currency>()
        currencyDTO.currencies.forEach { (code, name) ->
            currencies.add(Currency(0, code, name))
        }
        return currencies
    }

    fun convertExchangeDTO(exchangeDTO: ExchangeDTO) : List<Exchange> {
        val exchanges = mutableListOf<Exchange>()
        exchangeDTO.quotes.forEach { (code, value) ->
            val coinCode = code.replace(exchangeDTO.source, "")
            exchanges.add(Exchange(0, coinCode, value))
        }
        return exchanges
    }
}
