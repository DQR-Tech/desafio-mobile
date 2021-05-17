package br.com.dqrtech.currencylayerconverter.utils

import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyConverter {
    companion object {
        fun convert(value: BigDecimal, rateFrom: BigDecimal, rateTo: BigDecimal): BigDecimal {
            if (rateFrom.equals(rateTo)) return value;
            return BigDecimal.ONE.divide(rateFrom, 20, RoundingMode.HALF_EVEN).multiply(value).multiply(rateTo).setScale(2, RoundingMode.HALF_EVEN)
        }
    }
}