package com.example.desafio.data.local.repository

import com.example.desafio.data.local.dao.ConversorDao
import com.example.desafio.data.local.entity.ConversorEntity
import com.example.desafio.domain.model.ConversorDto
import com.example.desafio.utilis.JsonService

class ConversorDataSource(
    val conversorDao: ConversorDao
) : ConversorLocalRepository{
    override suspend fun insertConversor(conversorDto: ConversorDto): Long {
        val entity = ConversorEntity(
            moedas = JsonService.fromJsonConversor(conversorDto.moedas!!),
            success = conversorDto.success,
            terms = conversorDto.terms,
            privacy = conversorDto.privacy,
            timestamp = conversorDto.timestamp,
            source = conversorDto.source
        )
        return conversorDao.insertMoeda(entity)
    }

    override fun selectConversor(): ConversorDto {
        val entity = conversorDao.selectMoeda()
        val moedaDto = ConversorDto(
            moedas = JsonService.fromJsonConversor(entity.moedas),
            success = entity.success,
            terms = entity.terms,
            privacy = entity.privacy,
            timestamp = entity.timestamp,
            source = entity.source
        )
        return moedaDto
    }

    override fun verificarConversor(): Boolean = conversorDao.verificarMoeda()

    override suspend fun deleteConversor() = conversorDao.deleteMoedas()

}

interface ConversorLocalRepository {
    suspend fun insertConversor(conversorDto: ConversorDto) : Long

    fun selectConversor() : ConversorDto

    fun verificarConversor() : Boolean

    suspend fun deleteConversor()
}