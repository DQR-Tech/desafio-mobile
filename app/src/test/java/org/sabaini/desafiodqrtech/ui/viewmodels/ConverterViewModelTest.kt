package org.sabaini.desafiodqrtech.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.sabaini.desafiodqrtech.MainCoroutineRule
import org.sabaini.desafiodqrtech.Util.getListOfCurrencies
import org.sabaini.desafiodqrtech.entities.Currency
import org.sabaini.desafiodqrtech.entities.ExchangeRate
import org.sabaini.desafiodqrtech.getOrAwaitValueTest
import org.sabaini.desafiodqrtech.repositories.CurrencyRepository

@RunWith(MockitoJUnitRunner::class)
class ConverterViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: CurrencyRepository

    private lateinit var converterViewModel: ConverterViewModel

    private lateinit var currencies: List<Currency>
    private lateinit var rates: List<ExchangeRate>

    @Before
    fun setup() = runBlocking {
        currencies = getListOfCurrencies(15)
        rates = listOf(ExchangeRate("USD", "BRL", 2.0), ExchangeRate("USD", "EUR", 5.0))
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getCurrencies()).thenAnswer {
            return@thenAnswer flow {
                emit(currencies)
            }
        }
        Mockito.`when`(repository.getExchangeRates()).thenAnswer {
            return@thenAnswer flow {
                emit(rates)
            }
        }
        converterViewModel = ConverterViewModel(repository)

    }

    @Test
    fun testViewModelInitCurrencies() {
        assertThat(converterViewModel.currencies.getOrAwaitValueTest()).isEqualTo(currencies)
    }

    @Test
    fun testConvertWithEmptyCurrency() {
        converterViewModel.convert("", "", "0")
        assertThat(converterViewModel.inputError.getOrAwaitValueTest()).isNotEmpty()
    }

    @Test
    fun testConvertWithEqualCurrencies() {
        converterViewModel.convert("BRL", "BRL", "0")
        assertThat(converterViewModel.inputError.getOrAwaitValueTest()).isNotEmpty()
    }

    @Test
    fun testConvertWithEmptyValue() {
        converterViewModel.convert("USD", "BRL", "")
        assertThat(converterViewModel.inputError.getOrAwaitValueTest()).isNotEmpty()
    }

    @Test
    fun testConvertWithNegativeValue() {
        converterViewModel.convert("USD", "BRL", "-5")
        assertThat(converterViewModel.inputError.getOrAwaitValueTest()).isNotEmpty()
    }

    @Test
    fun testConvertWithValueEqualZero() {
        converterViewModel.convert("USD", "BRL", "0")
        assertThat(converterViewModel.inputError.getOrAwaitValueTest()).isNotEmpty()
    }

    @Test
    fun testConvertFromUSD() {
        converterViewModel.convert("USD", "BRL", "20")
        assertThat(converterViewModel.convertedValue.getOrAwaitValueTest()).isEqualTo(20 * 2)
    }

    @Test
    fun testConvertToUSD() {
        converterViewModel.convert("BRL", "USD", "20")
        assertThat(converterViewModel.convertedValue.getOrAwaitValueTest()).isEqualTo(20 / 2)
    }

    @Test
    fun testConvertWithoutUSD() {
        converterViewModel.convert("BRL", "EUR", "20")
        assertThat(converterViewModel.convertedValue.getOrAwaitValueTest()).isEqualTo((20 / 2) * 5)
    }
}