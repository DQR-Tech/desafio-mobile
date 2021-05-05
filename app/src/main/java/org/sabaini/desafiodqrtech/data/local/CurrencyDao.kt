package org.sabaini.desafiodqrtech.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurrencies(currencies: List<DatabaseCurrency>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveExchangeRate(rates: List<DatabaseExchangeRate>)

    @Query("select * from databasecurrency")
    fun loadCurrencies(): Flow<List<DatabaseCurrency>>

    @Query("select * from databaseexchangerate")
    fun loadExchangeRates(): Flow<List<DatabaseExchangeRate>>
}