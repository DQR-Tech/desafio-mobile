package dev.keader.coinconversor.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.keader.coinconversor.database.dao.CurrencyDAO
import dev.keader.coinconversor.database.dao.ExchangeDAO
import dev.keader.coinconversor.database.model.Currency
import dev.keader.coinconversor.database.model.Exchange

@Database(entities = [Currency::class, Exchange::class], version = 1, exportSchema = true)
abstract class CoinConverterDatabase : RoomDatabase() {
    abstract val currencyDAO: CurrencyDAO
    abstract val exchangeDAO: ExchangeDAO
}
