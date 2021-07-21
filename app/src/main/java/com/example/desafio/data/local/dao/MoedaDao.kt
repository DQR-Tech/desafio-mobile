package com.example.desafio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.desafio.data.local.entity.MoedaEntity

@Dao
interface MoedaDao {

    @Insert
    suspend fun insertMoeda(moedaEntity: MoedaEntity) : Long

    @Query("SELECT * FROM moeda WHERE moedas=:moedas")
    suspend fun validarMoeda(moedas : String) : MoedaEntity

    @Query("DELETE FROM moeda WHERE moedas =:moedas")
    suspend fun deleteMoedas(moedas : String)
}