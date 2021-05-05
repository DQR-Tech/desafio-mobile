package org.sabaini.desafiodqrtech

import org.sabaini.desafiodqrtech.entities.Currency

object Util {

    fun getListOfCurrencies(size: Int): List<Currency> {
        val list = mutableListOf<Currency>()
        for (i in 1..size) {
            list.add(
                Currency(getRandomString(3), getRandomString(10))
            )
        }
        return list
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}