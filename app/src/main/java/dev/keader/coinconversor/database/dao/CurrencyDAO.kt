package dev.keader.coinconversor.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.keader.coinconversor.database.model.Currency

@Dao
interface CurrencyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: Currency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currencies: List<Currency>)

    @Query("SELECT * FROM Currency ORDER BY name ASC")
    suspend fun getAllCurrenciesOrderByName() : List<Currency>

    @Query("SELECT * FROM Currency ORDER BY code ASC")
    suspend fun getAllCurrenciesOrderByCode() : List<Currency>

    // || in SQL = String concat
    @Query("SELECT * FROM Currency WHERE (code LIKE '%' || :search || '%') OR (name LIKE '%' || :search || '%') ORDER BY name ASC")
    suspend fun getCurrenciesBySearchOrderByName(search: String) : List<Currency>

    @Query("SELECT * FROM Currency WHERE (code LIKE '%' || :search || '%') OR (name LIKE '%' || :search || '%') ORDER BY code ASC")
    suspend fun getCurrenciesBySearchOrderByCode(search: String) : List<Currency>

    @Query("DELETE FROM Currency")
    suspend fun clearCurrencies()

    @Query("SELECT count(id) FROM Currency")
    suspend fun getCount() : Long
}
