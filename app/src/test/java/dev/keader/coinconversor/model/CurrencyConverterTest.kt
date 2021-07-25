package dev.keader.coinconversor.model

import com.google.common.truth.Truth.assertThat
import dev.keader.coinconversor.database.model.Exchange
import org.junit.Before
import org.junit.Test

class CurrencyConverterTest {

    private val currencyConverter = CurrencyConverter()
    private val ERROR = -1.0

    @Before
    fun setUp() {
        val map = mutableMapOf<String, Double>()
        map["BRL"] = 5.20
        map["ARS"] = 96.347
        map["USD"] = 1.0
        map["CHF"] = 0.92
        currencyConverter.updateCurrencyMap(map)
    }

    @Test
    fun `should return map size equals of inserted map`() {
        val cc = CurrencyConverter()
        val map1 = mutableMapOf<String, Double>()
        map1["BRL"] = 5.20
        map1["ARS"] = 96.34
        map1["USD"] = 1.0
        map1["CHF"] = 0.92
        val map2 = mutableMapOf<String, Double>()
        map1["ARS"] = 96.34
        map1["USD"] = 1.0
        val emptyMap = emptyMap<String, Double>()

        assertThat(cc.exchanges).isEmpty()

        cc.updateCurrencyMap(map1)
        assertThat(cc.exchanges).hasSize(map1.size)

        cc.updateCurrencyMap(map1)
        cc.updateCurrencyMap(map1)
        assertThat(cc.exchanges).hasSize(map1.size)

        cc.updateCurrencyMap(map2)
        assertThat(cc.exchanges).hasSize(map2.size)

        cc.updateCurrencyMap(emptyMap)
        assertThat(cc.exchanges).isEmpty()
    }

    @Test
    fun `should return map size equals of inserted list`() {
        val cc = CurrencyConverter()
        val list = mutableListOf<Exchange>()
        list.add(Exchange(0, "BRL", 5.0))
        list.add(Exchange(0, "USD", 1.0))
        list.add(Exchange(0, "ARS", 54.58))

        assertThat(cc.exchanges).isEmpty()

        cc.updateCurrencyMap(list)
        assertThat(cc.exchanges).hasSize(list.size)

        cc.updateCurrencyMap(list)
        assertThat(cc.exchanges).hasSize(list.size)

        assertThat(cc.exchanges["BRL"]).isEqualTo(5.0)
        assertThat(cc.exchanges["USD"]).isEqualTo(1.0)
        assertThat(cc.exchanges["ARS"]).isEqualTo(54.58)
    }

    @Test
    fun `should return error if list is not initialized`() {
        val cc = CurrencyConverter()
        val result = cc.convert("BRL", "ARS", 20.0)
        assertThat(result).isEqualTo(ERROR)
    }

    @Test
    fun `should return error if currency is not on list`() {
        var result = currencyConverter.convert("BRL", "UKI", 30.0)
        assertThat(result).isEqualTo(ERROR)
        result = currencyConverter.convert("UKI", "BRL", 30.0)
        assertThat(result).isEqualTo(ERROR)
        result = currencyConverter.convert("UKI", "UKI", 30.0)
        assertThat(result).isEqualTo(ERROR)
    }

    @Test
    fun `should return error if currency is empty`() {
        var result = currencyConverter.convert("", "UKI", 80.0)
        assertThat(result).isEqualTo(ERROR)
        result = currencyConverter.convert("UKI", "", 80.0)
        assertThat(result).isEqualTo(ERROR)
        result = currencyConverter.convert("", "", 80.0)
        assertThat(result).isEqualTo(ERROR)
    }

    @Test
    fun `should return same value if currencies are equals`() {
        var result = currencyConverter.convert("BRL", "BRL", 60.0)
        assertThat(result).isEqualTo(60.0)
        result = currencyConverter.convert("USD", "USD", 50.5898)
        assertThat(result).isEqualTo(50.5898)
    }

    @Test
    fun `should return correct value of conversion with USD as origin`() {
        var result = currencyConverter.convert("USD", "BRL", 67.47)
        assertThat(result).isAtLeast(350.84)
        result = currencyConverter.convert("USD", "ARS", 478.4789)
        assertThat(result).isAtLeast(46100.00)
        result = currencyConverter.convert("USD", "CHF", 7894.99)
        assertThat(result).isAtLeast(7263.38)
        result = currencyConverter.convert("USD", "USD", 87458.58746)
        assertThat(result).isEqualTo(87458.58746)
    }

    @Test
    fun `should return correct value of conversion without USD as origin`() {
        var result = currencyConverter.convert("BRL", "USD", 18.47)
        assertThat(result).isAtLeast(3.54)
        result = currencyConverter.convert("BRL", "ARS", 58.74)
        assertThat(result).isAtLeast(1088.35)
        result = currencyConverter.convert("BRL", "BRL", 5487545478754.54878454)
        assertThat(result).isEqualTo(5487545478754.54878454)
        result = currencyConverter.convert("CHF", "USD", 8745.478)
        assertThat(result).isAtLeast(9505.90)
    }
}
