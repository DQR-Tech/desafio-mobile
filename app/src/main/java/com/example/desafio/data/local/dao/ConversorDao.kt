package com.example.desafio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.desafio.data.local.entity.ConversorEntity
import com.example.desafio.data.local.entity.MoedaEntity

@Dao
interface ConversorDao {

    @Insert
    suspend fun insertMoeda(conversorEntity: ConversorEntity) : Long

    @Query("SELECT * FROM conversor WHERE id=1")
    fun verificarMoeda() : Boolean

    @Query("SELECT * FROM conversor WHERE id=1")
    fun selectMoeda() : ConversorEntity

    @Query("DELETE FROM conversor WHERE id = 1")
    suspend fun deleteMoedas()
}