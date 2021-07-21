package com.example.desafio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.desafio.data.local.entity.MoedaEntity

@Dao
interface MoedaDao {

    @Insert
    suspend fun insertMoeda(moedaEntity: MoedaEntity) : Long

    @Query("SELECT * FROM moeda WHERE id=1")
    fun verificarMoeda() : Boolean

    @Query("SELECT * FROM moeda WHERE id=1")
    fun selectMoeda() : MoedaEntity

    @Query("DELETE FROM moeda WHERE id = 1")
    suspend fun deleteMoeda()
}