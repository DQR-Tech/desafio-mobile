package com.example.desafio.domain.usecase.local.conversor

import com.example.desafio.data.local.repository.ConversorLocalRepository
import com.example.desafio.domain.model.ConversorDto

class InsertConversorImpl(
    val repository: ConversorLocalRepository
): InsertConversorUsecase {
    override suspend fun invoke(conversorDto: ConversorDto): Long = repository.insertConversor(conversorDto)
}

interface InsertConversorUsecase {
    suspend operator fun invoke(conversorDto: ConversorDto): Long
}