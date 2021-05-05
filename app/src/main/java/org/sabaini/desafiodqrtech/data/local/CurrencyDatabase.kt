package org.sabaini.desafiodqrtech.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DatabaseCurrency::class, DatabaseExchangeRate::class], version = 5)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}