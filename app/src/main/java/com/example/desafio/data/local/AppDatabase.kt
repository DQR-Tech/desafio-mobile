package com.example.desafio.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.desafio.data.local.dao.ConversorDao
import com.example.desafio.data.local.dao.MoedaDao
import com.example.desafio.data.local.entity.ConversorEntity
import com.example.desafio.data.local.entity.MoedaEntity

@Database(
    entities = [MoedaEntity::class, ConversorEntity::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val moedaDao:MoedaDao
    abstract val conversorDao:ConversorDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room
                        .databaseBuilder(context, AppDatabase::class.java, "moeda_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return instance
            }
        }
    }
}