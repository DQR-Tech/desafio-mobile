package dev.keader.coinconversor.model

import dev.keader.coinconversor.database.model.Exchange
import timber.log.Timber
import java.math.RoundingMode

class CurrencyConverter {

    private val exchangeMap = mutableMapOf<String, Double>()

    fun updateCurrencyMap(newMap: Map<String, Double>) {
        exchangeMap.clear()
        exchangeMap.putAll(newMap)
    }

    fun updateCurrencyMap(exchangeList: List<Exchange>) {
        if (exchangeList.isEmpty())
            return

        exchangeMap.clear()
        exchangeList.forEach {
            exchangeMap[it.code] = it.value
        }
    }

    fun convert(origin: String, destination: String, value: Double) : Double {
        // Sanity checks
        // It's called by Convert button, and button has validation, so exchangeMap should be never
        // at this point.
        if (exchangeMap.isEmpty())
            return ERROR_VALUE
        if (!exchangeMap.containsKey(origin) || !exchangeMap.containsKey(destination))
            return ERROR_VALUE
        if (origin == destination || value == 0.0)
            return value

        // API Limitation: origin values != USD, must be converted to USD first
        val result = if (origin == "USD") {
            convertToDestination(destination, value)
        } else {
            val usdValue = convertToUSD(origin, value)
            convertToDestination(destination, usdValue)
        }

        val scale = if (result < 0.001) 10 else 3
        val approximately = result.toBigDecimal().setScale(scale, RoundingMode.HALF_EVEN).toDouble()
        Timber.d("Result of $origin -> $destination is: $result ~($approximately)")
        return approximately
    }

    private fun convertToDestination(destination: String, value: Double): Double {
        val exchangeValue = exchangeMap.getValue(destination)
        return value * exchangeValue
    }

    private fun convertToUSD(currency: String, value: Double) : Double {
        val exchangeValue = exchangeMap.getValue(currency)
        return value / exchangeValue
    }

    companion object {
        const val ERROR_VALUE = -1.0
    }
}
