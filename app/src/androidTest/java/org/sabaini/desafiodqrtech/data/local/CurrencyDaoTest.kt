package org.sabaini.desafiodqrtech.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.sabaini.desafiodqrtech.MainCoroutineRuleAndroidTest

@RunWith(AndroidJUnit4::class)
class CurrencyDaoTest {
    private lateinit var database: CurrencyDatabase
    private lateinit var currencyDao: CurrencyDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRuleAndroidTest()

    @Before
    fun createDb() = runBlocking {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CurrencyDatabase::class.java,
        ).build()
        currencyDao = database.currencyDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testSaveAndLoadCurrencies() = runBlocking {
        val dc1 = DatabaseCurrency("BRL", "Real")
        val dc2 = DatabaseCurrency("USD", "Dollar")
        val l = listOf(dc1, dc2)
        currencyDao.saveCurrencies(l)
        val currencies = currencyDao.loadCurrencies().take(1).toList()[0]
        assertThat(currencies).isEqualTo(l)
    }

    @Test
    fun testSaveAndLoadExchangeRate() = runBlocking {
        val er1 = DatabaseExchangeRate("USD", "BRL", 0.0)
        val er2 = DatabaseExchangeRate("USD", "EUR", 0.0)
        val l = listOf(er1, er2)
        currencyDao.saveExchangeRate(l)
        val rates = currencyDao.loadExchangeRates().take(1).toList()[0]
        assertThat(rates).isEqualTo(l)
    }

}