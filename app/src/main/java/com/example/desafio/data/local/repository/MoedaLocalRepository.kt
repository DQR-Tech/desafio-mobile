package com.example.desafio.data.local.repository

import com.example.desafio.data.local.dao.MoedaDao
import com.example.desafio.data.local.entity.MoedaEntity
import com.example.desafio.domain.model.MoedasDto

class MoedaDataSource(
    private val moedaDao: MoedaDao
) : MoedaLocalRepository{

    override suspend fun insertMoeda(moedasDto: MoedasDto): Long {
        val entity = MoedaEntity(
            moedas = moedasDto.moedas!!.toString(),
            success = moedasDto.success,
            terms = moedasDto.terms,
            privacy = moedasDto.privacy
        )
        return moedaDao.insertMoeda(entity)
    }

    override suspend fun validarMoeda(moedas: Map<String, String>): MoedaEntity {
        return moedaDao.validarMoeda(moedas.toString())
    }

    override suspend fun deleteMoedas(moedas: Map<String, String>) {
        moedaDao.deleteMoedas(moedas.toString())
    }
}

interface MoedaLocalRepository {
    suspend fun insertMoeda(moedasDto: MoedasDto) : Long

    suspend fun validarMoeda(moedas : Map<String, String>) : MoedaEntity

    suspend fun deleteMoedas(moedas : Map<String, String>)
}