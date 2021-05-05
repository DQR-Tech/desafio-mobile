package org.sabaini.desafiodqrtech.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sabaini.desafiodqrtech.data.local.CurrencyDao
import org.sabaini.desafiodqrtech.data.local.CurrencyDatabase
import org.sabaini.desafiodqrtech.data.remote.CurrencyLayerApi
import org.sabaini.desafiodqrtech.repositories.CurrencyRepository
import org.sabaini.desafiodqrtech.utilities.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyRepository(dao: CurrencyDao, api: CurrencyLayerApi): CurrencyRepository {
        return CurrencyRepository(dao, api)
    }

    @Singleton
    @Provides
    fun provideCurrencyDatabase(@ApplicationContext context: Context): CurrencyDatabase {
        return Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java,
            "currency_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCurrencyDao(database: CurrencyDatabase): CurrencyDao {
        return database.currencyDao()
    }

    @Singleton
    @Provides
    fun provideCurrencyLayerApi(): CurrencyLayerApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CurrencyLayerApi::class.java)
    }
}