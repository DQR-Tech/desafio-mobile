package br.com.dqrtech.currencylayerconverter.utils

class CurrencyConverter {
    companion object {
        fun convert(value: Double, rateFrom: Double, rateTo: Double): Double {
            return 1 / rateFrom * value * rateTo
        }
    }
}