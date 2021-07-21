package com.example.desafio.data.local.repository

import com.example.desafio.data.local.dao.MoedaDao
import com.example.desafio.data.local.entity.MoedaEntity
import com.example.desafio.domain.model.MoedasDto
import com.example.desafio.utilis.JsonService

class MoedaDataSource(
    private val moedaDao: MoedaDao
) : MoedaLocalRepository {

    override suspend fun insertMoeda(moedasDto: MoedasDto): Long {
        val entity = MoedaEntity(
            moedas = JsonService.fromJson(moedasDto.moedas!!),
            success = moedasDto.success,
            terms = moedasDto.terms,
            privacy = moedasDto.privacy
        )
        return moedaDao.insertMoeda(entity)
    }

    override fun selectMoeda(): MoedasDto {
        val entity = moedaDao.selectMoeda()
        val moedaDto = MoedasDto(
            success = entity.success,
            terms = entity.terms,
            privacy = entity.privacy,
            moedas = JsonService.fromMoedaMap(entity.moedas)
        )
        return moedaDto
    }

    override fun verificarMoeda(): Boolean = moedaDao.verificarMoeda()

    override suspend fun deleteMoedas() = moedaDao.deleteMoedas()
}

interface MoedaLocalRepository {
    suspend fun insertMoeda(moedasDto: MoedasDto) : Long

    fun selectMoeda() : MoedasDto

    fun verificarMoeda() : Boolean

    suspend fun deleteMoedas()
}