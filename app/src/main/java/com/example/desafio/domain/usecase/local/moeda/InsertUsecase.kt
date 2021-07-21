package com.example.desafio.domain.usecase.local

import com.example.desafio.data.local.repository.MoedaLocalRepository
import com.example.desafio.domain.model.MoedasDto

class InsertImpl(
    val repository: MoedaLocalRepository
):InsertUsecase{
    override suspend fun invoke(moedasDto: MoedasDto): Long = repository.insertMoeda(moedasDto)
}

interface InsertUsecase {
    suspend operator fun invoke(moedasDto: MoedasDto): Long
}