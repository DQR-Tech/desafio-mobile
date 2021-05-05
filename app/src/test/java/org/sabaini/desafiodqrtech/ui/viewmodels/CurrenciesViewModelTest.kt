package org.sabaini.desafiodqrtech.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
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
class CurrenciesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: CurrencyRepository

    private lateinit var currenciesViewModel: CurrenciesViewModel

    private lateinit var currencies: List<Currency>

    @Before
    fun setup() = runBlocking {
        currencies = getListOfCurrencies(15)
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getCurrenciesFromDatabase()).thenAnswer {
            return@thenAnswer flow {
                emit(currencies)
            }
        }
        currenciesViewModel = CurrenciesViewModel(repository)

    }

    @Test
    fun testViewModelInit() {
        Truth.assertThat(currenciesViewModel.currencies.getOrAwaitValueTest()).isEqualTo(currencies)
    }
}