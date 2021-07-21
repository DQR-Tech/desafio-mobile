package com.example.desafio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.desafio.data.local.entity.ConversorEntity
import com.example.desafio.data.local.entity.MoedaEntity

@Dao
interface ConversorDao {

    @Insert
    suspend fun insertConversor(conversorEntity: ConversorEntity) : Long

    @Query("SELECT * FROM conversor WHERE id=1")
    fun verificarConversor() : Boolean

    @Query("SELECT * FROM conversor WHERE id=1")
    fun selectConversor() : ConversorEntity

    @Query("DELETE FROM conversor WHERE id = 1")
    suspend fun deleteConversor()
}