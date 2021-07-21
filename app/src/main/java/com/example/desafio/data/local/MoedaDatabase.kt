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
    version = 3)
abstract class MoedaDatabase : RoomDatabase() {

    abstract val moedaDao:MoedaDao
    abstract val conversorDao:ConversorDao

    companion object {

        private var INSTANCE: MoedaDatabase? = null

        fun getInstance(context: Context): MoedaDatabase {
            synchronized(this) {
                var instance: MoedaDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room
                        .databaseBuilder(context, MoedaDatabase::class.java, "moeda_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return instance
            }
        }
    }
}